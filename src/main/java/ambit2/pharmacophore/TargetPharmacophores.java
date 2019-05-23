package ambit2.pharmacophore;

import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.pharmacophore.features.FeatureInstance;

public class TargetPharmacophores 
{
	public IAtomContainer taget = null;
	public List<FeatureInstance> featureInstances = new ArrayList<FeatureInstance>();
	public double distances[][] = null;
	
	public void calcDistanceMatrix()
	{
		//TODO
	}
}
