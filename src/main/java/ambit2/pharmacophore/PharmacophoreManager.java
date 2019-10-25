package ambit2.pharmacophore;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;

import ambit2.pharmacophore.isomorphism.TargetFeatureGraph;

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
		//TODO
		
		//Iterate all pharmacophore feature: 
		//Find all feature instances using GrpupMatch.getMappings (Ambit-SMARTS isomophism)
		
		return null;
	}
	
	
}
