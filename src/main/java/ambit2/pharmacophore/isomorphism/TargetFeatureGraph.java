package ambit2.pharmacophore.isomorphism;

import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.pharmacophore.features.FeatureInstance;

/**
 * 
 * @author nick
 * Describes a full graph of all feature instances found in a particular 
 * target molecule and all distances/connections (3D or 2D) between the
 * feature instances 
 */

public class TargetFeatureGraph 
{
	
	public IAtomContainer taget = null;
	public List<FeatureInstance> featureInstances = new ArrayList<FeatureInstance>();
	public double distances[][] = null;
	public boolean FlagTopologicalDistances = false;
	
	public void calcDistanceMatrix()
	{
		calcDistanceMatrix(false);
	}
	
	public void calcDistanceMatrix(boolean topDistances)
	{
		if(topDistances) {
			
		}
		
		else
		{
			
		}
		
	}
	
}
