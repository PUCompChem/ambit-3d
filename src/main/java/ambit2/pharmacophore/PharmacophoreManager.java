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
	
	
	public TargetFeatureGraph getTargetFeatureGraph(Pharmacophore pharmacophore, IAtomContainer target, List<String> errors)
	{
		
		SmartsParser smartsParser = new SmartsParser();
		IsomorphismTester isomorphismTester = new IsomorphismTester();
		
		TargetFeatureGraph targetFeatureGraph = new TargetFeatureGraph();
		ArrayList<IFeature> features = pharmacophore.getFeatures();
		List<List<IAtom>> instancesList = null;
		 
		for (int i = 0; i < features.size(); i++) {
			
			String smarts = "";
			if(features.get(i).getType() == Type.SMARTS_GROUP) {
				SmartsGroupFeature sgf = (SmartsGroupFeature) features.get(i);
				smarts = sgf.getSmarts();
			}else {
				errors.add("Pharmacophore: "+pharmacophore.getName()+" feature type is not supported");
				break;
			}
			
			GroupMatch groupMatch = new GroupMatch(smarts, smartsParser,isomorphismTester);
			instancesList = groupMatch.getMappings(target);
			
		}
		
		for (int i = 0; i < instancesList.size(); i++) {
			FeatureInstance currentFeatureInstance = new FeatureInstance();
			currentFeatureInstance.setAtoms(instancesList.get(i));
			targetFeatureGraph.featureInstances.add(currentFeatureInstance);
			 
		}
		return targetFeatureGraph;
	}
	
	
}
