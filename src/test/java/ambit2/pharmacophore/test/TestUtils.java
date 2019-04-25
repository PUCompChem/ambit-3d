package ambit2.pharmacophore.test;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.smarts.SmartsHelper;

public class TestUtils {

	public static void main(String[] args) throws Exception 
	{
	
		
		IAtomContainer mol = SmartsHelper.getMoleculeFromSmiles("CCCCO");
		System.out.println(mol.getAtomCount());
		
		IAtom a = mol.getAtom(0);
		
		Point3d p = a.getPoint3d();
		
	}

}
