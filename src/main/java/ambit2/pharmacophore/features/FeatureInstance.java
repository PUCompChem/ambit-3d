package ambit2.pharmacophore.features;

import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;

public class FeatureInstance 
{
	public IFeature feature = null;
	public List<IAtom> atoms = null;
	public Point3d instance3dCoordinates = null;
	
	public IFeature getFeature() {
		return feature;
	}
	public void setFeature(IFeature feature) {
		this.feature = feature;
	}
	public List<IAtom> getAtoms() {
		return atoms;
	}
	public void setAtoms(List<IAtom> atoms) {
		this.atoms = atoms;
	}
	public Point3d getInstance3dCoordinates() {
		return instance3dCoordinates;
	}
	public void setInstance3dCoordinates(Point3d instance3dCoordinates) {
		this.instance3dCoordinates = instance3dCoordinates;
	}
	
	
}
