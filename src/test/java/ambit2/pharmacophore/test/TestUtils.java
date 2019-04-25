package ambit2.pharmacophore.test;

import org.openscience.cdk.interfaces.IAtomContainer;

import ambit2.smarts.SmartsHelper;

public class TestUtils {

	public static void main(String[] args) throws Exception 
	{
	
		
		IAtomContainer mol = SmartsHelper.getMoleculeFromSmiles("CCCCO");
		System.out.println(mol.getAtomCount());

	}

}
