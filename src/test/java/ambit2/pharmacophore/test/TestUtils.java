package ambit2.pharmacophore.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point3d;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.pharmacophore.Pharmacophore;
import ambit2.smarts.SmartsHelper;

public class TestUtils {

	public static void main(String[] args) throws Exception 
	{
		
		testPharmacophoreJSON("./test.json");
	}
	
	
	public static void test0() throws Exception 
	{
		IAtomContainer mol = SmartsHelper.getMoleculeFromSmiles("CCCCO");
		System.out.println(mol.getAtomCount());
		
		IAtom a = mol.getAtom(0);
		
		Point3d p = a.getPoint3d();
	}
	
	public static void testPharmacophoreJSON(String fileName) throws Exception
	{
		List<String> errors = new ArrayList<String>();
		FileInputStream fin = new FileInputStream(fileName); 
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		root = mapper.readTree(fin);
		Pharmacophore pharmR = Pharmacophore.extractPharmacophoreFromJson(root, errors);
		
		System.out.println(pharmR.toJSONKeyWord(""));
		
		fin.close();
	}

}
