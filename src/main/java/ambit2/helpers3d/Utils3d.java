package ambit2.helpers3d;

import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;

public class Utils3d 
{
	public static Point3d getAtomListCenterOfMass(List<IAtom> atoms)
	{
		double x = 0.0;
	    double y = 0.0;
	    double z = 0.0;
		for (int i = 0; i < atoms.size(); i++) {
			Point3d point = atoms.get(i).getPoint3d();
			 x += point.getX();
			 y += point.getY();
			 z += point.getZ();
		}
		
		double avrX = x/atoms.size();
		double avrY = y/atoms.size();
		double avrZ = z/atoms.size();
		
		Point3d point = new Point3d(avrX,avrY,avrZ);
		return point;
	}
}