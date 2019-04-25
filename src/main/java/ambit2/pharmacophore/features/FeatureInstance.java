package ambit2.pharmacophore.features;

import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;

public class FeatureInstance 
{
	List<IAtom> atoms = null;
	Point3d instance3dCoordinates = null;
	
	public void calc3dCoordicatesFromAtoms()
	{
		//TODO
	}
}
