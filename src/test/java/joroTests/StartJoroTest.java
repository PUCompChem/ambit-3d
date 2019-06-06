package joroTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.helpers3d.json.JsonUtils;
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
		 

		File jsonFile = new File("./test.json");
		FileInputStream fin = new FileInputStream(jsonFile); 
		
//				 connection1.setName("\"testtasd\"");
//			Pharmacophore pharmW = new Pharmacophore(features,conections);
//			pharmW.setFeatures(features);
//			pharmW.setConections(conections);
//			if (jsonFile.exists())
//			{
//				  FileWriter writer = new FileWriter(jsonFile);
//			      writer.write(pharmW.toJSONKeyWord(""));
//			      writer.close();
//			}
//			else
//			{	
//				System.out.println("File ./test.json not found!");
//				 
//			}	
			
			
			
			
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = null;
			root = mapper.readTree(fin);
			Pharmacophore pharmR = Pharmacophore.extractPharmacophoreFromJson(root);
			System.out.println(pharmR.getConections().get(0).getName());
			JsonNode infoNode = root.path("FEATURES");
			JsonNode branch = infoNode.get(0);
			String branch2 = infoNode.get(0).get("FEATURE_NAME").textValue();
			System.out.println("infoNode ="+ branch2);

			 
		 fin.close();
			
			 
			
 		 


	}

}
