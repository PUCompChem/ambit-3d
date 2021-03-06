package ambit2.pharmacophore.isomorphism;

import java.util.ArrayList;
import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.helpers3d.Utils3d;
import ambit2.pharmacophore.Pharmacophore;
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
	public Pharmacophore pharmacophore = null;
	public IAtomContainer target = null;
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
			distances = new double[featureInstances.size()][featureInstances.size()];
			for (int i = 0; i < featureInstances.size(); i++) {
				for (int j = 0; j < featureInstances.size(); j++) {
					if(i == j) {
						distances[i][j] = 0;
					}
					else {
						distances[i][j] = Utils3d.distance3d(featureInstances.get(i).getInstance3dCoordinates(), featureInstances.get(j).getInstance3dCoordinates());
					}
				}
			}
		}
		
	}
	
	public String toString() {
		StringBuilder result  = new StringBuilder();
		
		for (int i = 0; i < featureInstances.size(); i++) {
			FeatureInstance currentInstance = featureInstances.get(i);
			result.append("\t");
			result.append("feature ["+i+"]"+ " instances:\n");
			result.append("\t\t");
			for (int j = 0; j < currentInstance.getAtoms().size(); j++) {
					IAtom a = currentInstance.getAtoms().get(j);
					result.append(a.getSymbol() + (target.indexOf(a)+1) + " ");
			}
			result.append("\n");
			 
		}
		return result.toString();
	}
	
}
