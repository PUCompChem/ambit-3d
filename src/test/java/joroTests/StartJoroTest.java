package joroTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.pharmacophore.Pharmacophore;
import ambit2.pharmacophore.features.DistanceFeatureConnection;
import ambit2.pharmacophore.features.IFeature;
import ambit2.pharmacophore.features.IFeatureConnection;
import ambit2.pharmacophore.features.SmartsGroupFeature;

public class StartJoroTest {

	public static void main(String[] args) throws Exception {
		
		SmartsGroupFeature feature1 = new SmartsGroupFeature();
		SmartsGroupFeature feature2 = new SmartsGroupFeature();
		SmartsGroupFeature feature3 = new SmartsGroupFeature();
		SmartsGroupFeature feature4 = new SmartsGroupFeature();
		
		ArrayList<IFeature> features = new ArrayList<IFeature>();
		 features.add(feature1);
		 features.add(feature2);
		 features.add(feature3);
		 features.add(feature4);
		 
		 
		 DistanceFeatureConnection connection1 = new DistanceFeatureConnection(null, null, 0, 0);
		 DistanceFeatureConnection connection2 = new DistanceFeatureConnection(null, null, 0, 0);
		 DistanceFeatureConnection connection3 = new DistanceFeatureConnection(null, null, 0, 0);
		 DistanceFeatureConnection connection4 = new DistanceFeatureConnection(null, null, 0, 0);
		 ArrayList<IFeatureConnection> conections = new ArrayList<IFeatureConnection>();
		 conections.add(connection1);
		 conections.add(connection2);
		 conections.add(connection3);
		 conections.add(connection4);
		 
			Pharmacophore pharm = new Pharmacophore(features,conections);
//			System.out.println(pharm.toJSONKeyWord(""));
		 
			
			File jsonFile = new File("./test.json");
			JsonNode root = null;
			FileInputStream fin = new FileInputStream(jsonFile); 
			ObjectMapper mapper = new ObjectMapper();
			
			root = mapper.readTree(fin);
			fin.close(); 
			Pharmacophore pharm1 = pharm.extractPharmacophoreFromJson(root);

			System.out.println(pharm1.getPharmacophoreName());
			
			
//			if (jsonFile.exists())
//			{
//				  FileWriter writer = new FileWriter(jsonFile);
//			      writer.write(pharm.toJSONKeyWord(""));
//			      writer.close();
//			}
//			else
//			{	
//				System.out.println("File ./test.json not found!");
//				 
//			}	
		 


	}

}