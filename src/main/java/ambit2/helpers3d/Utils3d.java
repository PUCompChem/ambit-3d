package ambit2.helpers3d;

import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;

public class Utils3d 
{
	public static Point3d getCenterOfMass(List<IAtom> atoms)
	{
		double x = 0.0;
	    double y = 0.0;
	    double z = 0.0;
	    double M = 0.0;
	    
		for (IAtom at : atoms) {
			Point3d point = at.getPoint3d();
			double m = at.getExactMass();
			M += m;
			x += point.getX()/m;
			y += point.getY()/m;
			z += point.getZ()/m;
		}
		
		x = x/M;
		y = y/M;
		z = z/M;
		
		Point3d point = new Point3d(x,y,z);
		return point;
	}
	
	
	public static Point3d getAvergageCoordinate(List<IAtom> atoms)
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
		
		x = x/atoms.size();
		y = y/atoms.size();
		z = z/atoms.size();
		
		Point3d point = new Point3d(x,y,z);
		return point;
	}
	
	
	public static double distance3d(Point3d firstPoint, Point3d secondPoint) {
		Double distance = (Math.pow((secondPoint.getX()-firstPoint.getX()),2)+Math.pow((secondPoint.getY()-firstPoint.getY()),2)+Math.pow((secondPoint.getZ()-firstPoint.getZ()),2))/2;
		return distance;
		
	} 
	
	
}