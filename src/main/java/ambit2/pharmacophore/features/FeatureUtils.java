package ambit2.pharmacophore.features;

import javax.vecmath.Point3d;

import ambit2.helpers3d.Utils3d;

public class FeatureUtils 
{
	public static Point3d calcFeatuteCoordinates(FeatureInstance finstance)
	{
		switch (finstance.feature.getFeatureCoordinatesAlgorithm())
		{
		
			case MASS_CENTER:
				return Utils3d.getCenterOfMass(finstance.atoms);
		 
			case AVERAGE:
				return	Utils3d.getAvergageCoordinate(finstance.atoms);
			
			default: return null;
		}
	}
}
