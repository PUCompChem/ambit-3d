package ambit2.pharmacophore;

 
import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;

import ambit2.pharmacophore.features.FeatureInstance;
import ambit2.pharmacophore.features.IFeature;
import ambit2.pharmacophore.features.IFeature.Type;
import ambit2.pharmacophore.features.SmartsGroupFeature;
import ambit2.pharmacophore.isomorphism.TargetFeatureGraph;
import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;

public class PharmacophoreManager 
{
	protected IChemObjectBuilder builder;
	protected PharmacophoreDataBase pharmDataBase;  
	public PharmacophoreManager() {
		this.builder = SilentChemObjectBuilder.getInstance();
	}
	
	public PharmacophoreManager(IChemObjectBuilder builder) {
		if (builder == null)
			this.builder = SilentChemObjectBuilder.getInstance();
		else
			this.builder = builder;
	}
	
	
	public TargetFeatureGraph getTargetFeatureGraph(Pharmacophore pharmacophore, IAtomContainer target)
	{
		TargetFeatureGraph targetFeatureGraph = new TargetFeatureGraph();
		ArrayList<IFeature> features = pharmacophore.getFeatures();
		 
		for (int i = 0; i < features.size(); i++) 
		{
			switch (features.get(i).getType())
			{
			case SMARTS_GROUP: 
				SmartsGroupFeature sgf = (SmartsGroupFeature) features.get(i);
				if (sgf.getGroupMatch() != null)
				{
					List<List<IAtom>> maps = sgf.getGroupMatch().getMappings(target);
					
					for (int k = 0; k < maps.size(); i++) {
						FeatureInstance currentFeatureInstance = new FeatureInstance();
						currentFeatureInstance.setAtoms(maps.get(i));
						targetFeatureGraph.featureInstances.add(currentFeatureInstance);
						 
					}
				}
				else if (sgf.getGroupMatchList() != null)
				{
					for (GroupMatch groupMatch : sgf.getGroupMatchList())
					{
						List<List<IAtom>> maps = groupMatch.getMappings(target);
						
						for (int k = 0; k < maps.size(); i++) {
							FeatureInstance currentFeatureInstance = new FeatureInstance();
							currentFeatureInstance.setAtoms(maps.get(i));
							targetFeatureGraph.featureInstances.add(currentFeatureInstance);
							 
						}
					}
				}
				break;
			
			default:
				break;
			}
		}
		
		return targetFeatureGraph;
	}

	
	
	
}
