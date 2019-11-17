package ambit2.pharmacophore.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.vecmath.Point3d;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.Kekulization;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.IChemObjectReaderErrorHandler;
import org.openscience.cdk.io.IChemObjectReader.Mode;
import org.openscience.cdk.io.iterator.IIteratingChemObjectReader;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.base.exceptions.AmbitIOException;
import ambit2.core.helper.CDKHueckelAromaticityDetector;
import ambit2.core.io.FileInputState;
import ambit2.core.io.InteractiveIteratingMDLReader;
import ambit2.pharmacophore.Pharmacophore;
import ambit2.pharmacophore.PharmacophoreDataBase;
import ambit2.pharmacophore.PharmacophoreManager;
import ambit2.pharmacophore.isomorphism.TargetFeatureGraph;
import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsHelper;
import ambit2.smarts.SmartsParser;

public class TestUtils {

	public static void main(String[] args) throws Exception 
	{

		//testPharmacophoreJSON("./test.json");
		//testPharmacophoreDB("./test-pharmacophore-db.json");
		
		//testReadMoleculeFromFile("/home/developer/Desktop/mol1.mol");
		//testTargetFeatureGraph(null,"/home/mech/Desktop/mol1.mol","./test-pharmacophore-db.json" );
	}

	public static void testTargetFeatureGraph(
			String pharmName, 
			String moleculeFile, String pharmacophoreDBFile) throws Exception
	{
		PharmacophoreManager pharmacophoreManager = new PharmacophoreManager();

		//setting pharmacopohre DB
		PharmacophoreDataBase pharmacophoreDB = new PharmacophoreDataBase(pharmacophoreDBFile);

		if (!pharmacophoreDB.errors.isEmpty())
		{
			System.out.println("JSON errors:");
			for (String err: pharmacophoreDB.errors)
				System.out.println(err);
			System.out.println();
			return;
		}

		//setting target molecule
		IAtomContainer targetMolecule = getMoleculeFromFile(moleculeFile);

		//matching
		Pharmacophore p = pharmacophoreDB.getPharmacophore(pharmName);
		if (p == null)
		{	
			System.out.println("Pharmacophore: " + pharmName + " not present in database!");
			return;
		}
		
		TargetFeatureGraph currentFeatureGraph = pharmacophoreManager.getTargetFeatureGraph(p, targetMolecule);

		System.out.println("Pharmacophore: " + pharmName + "match:\n" +currentFeatureGraph.toString());
		 

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
		Pharmacophore pharmR = Pharmacophore.extractPharmacophoreFromJson(root, errors, "Test single pharmacophore: ");


		if (!errors.isEmpty())
		{	
			System.out.println("JSON parsing errors:");
			for (String err : errors)
				System.out.println(err);
		}	
		else
			System.out.println(pharmR.toJSONKeyWord(""));

		fin.close();
	}

	public static void testPharmacophoreDB(String fileName) throws Exception
	{
		PharmacophoreDataBase pharmacophoreDB = new PharmacophoreDataBase(fileName);

		if (!pharmacophoreDB.errors.isEmpty())
		{
			System.out.println("JSON errors:");
			for (String err: pharmacophoreDB.errors)
				System.out.println(err);

			System.out.println();
		}

		System.out.println(pharmacophoreDB.getQuickInfo());
		System.out.println();

		System.out.println(pharmacophoreDB.toJSONKeyWord(""));

	}
	
	public static void testReadMoleculeFromFile(String fileName) throws Exception
	{
		IAtomContainer mol = getMoleculeFromFile(fileName);
		
		for (int i = 0; i < mol.getAtomCount(); i++)
		{
			IAtom a = mol.getAtom(i);
			Point3d p = a.getPoint3d();
			System.out.println("Atom #" + (i+1) + "  " + p);
		}
	}

	public static IAtomContainer getMoleculeFromFile(String fileName) throws Exception
	{
		File file = new File(fileName);
		if (!file.exists()) 
			throw new FileNotFoundException(file.getAbsolutePath());
		InputStream in = new FileInputStream(file);
		IIteratingChemObjectReader<IAtomContainer> reader = null;

		IAtomContainer molecule = null;
		
		try 
		{
			reader = getReader(in,file.getName());
			
			if (reader.hasNext()) 
			{	
				molecule  = reader.next();

				if (molecule != null)
				{	
					try {
						AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(molecule);
						CDKHueckelAromaticityDetector.detectAromaticity(molecule);
						//implicit H count is NULL if read from InChI ...
						
						/*
						molecule = AtomContainerManipulator.removeHydrogens(molecule);
						CDKHydrogenAdder.getInstance(molecule.getBuilder()).addImplicitHydrogens(molecule);
						
						boolean aromatic = false;
						for (IBond bond : molecule.bonds()) if (bond.getFlag(CDKConstants.ISAROMATIC)) {aromatic = true; break;}
						if (aromatic)
							Kekulization.kekulize(molecule);
						*/	
					} 
					catch (Exception x) {
					}
				}
			}
		} 
		catch (Exception x1) {

		} 
		finally {
			try { reader.close(); } catch (Exception x) {}
		}
		
		return molecule;
	}

	protected static IIteratingChemObjectReader<IAtomContainer> getReader(InputStream in, String extension) throws CDKException, AmbitIOException {
		FileInputState instate = new FileInputState();
		IIteratingChemObjectReader<IAtomContainer> reader ;
		if (extension.endsWith(FileInputState._FILE_TYPE.SDF_INDEX.getExtension())) {
			reader = new InteractiveIteratingMDLReader(in,SilentChemObjectBuilder.getInstance());
			((InteractiveIteratingMDLReader) reader).setSkip(true);
		} else reader = instate.getReader(in,extension);

		reader.setReaderMode(Mode.RELAXED);
		reader.setErrorHandler(new IChemObjectReaderErrorHandler() {

			//@Override
			public void handleError(String message, int row, int colStart, int colEnd,
					Exception exception) {
				exception.printStackTrace();
			}

			//@Override
			public void handleError(String message, int row, int colStart, int colEnd) {
				System.out.println(message);
			}

			//@Override
			public void handleError(String message, Exception exception) {
				exception.printStackTrace();				
			}

			//@Override
			public void handleError(String message) {
				System.out.println(message);
			}
		});
		return reader;
	}	

}
