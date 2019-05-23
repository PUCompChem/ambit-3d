package ambit2.pharmacophore.features;

import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;

public class FeatureInstance 
{
	public IFeature feature = null;
	public List<IAtom> atoms = null;
	public Point3d instance3dCoordinates = null;
	
	
	public void calc3dCoordicatesFromAtoms()
	{
		//TODO
	}
	
}
