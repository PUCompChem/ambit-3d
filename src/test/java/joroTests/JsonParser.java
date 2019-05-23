package joroTests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.pharmacophore.Pharmacophore;
 
 
 
 

public class JsonParser {
	
	List<String> errors = new ArrayList<String>();
	JsonUtilities jsonUtils = new JsonUtilities();
	public Pharmacophore loadFromJSON(File jsonFile) throws FileNotFoundException {
		JsonNode root = null;
		ObjectMapper mapper = new ObjectMapper();
		FileInputStream fin = new FileInputStream(jsonFile); 
	 
		
		
		
		
		try {
			root = mapper.readTree(fin);
		} catch (Exception x) {
		 
		} finally {
			try {fin.close();} catch (Exception x) {}	
		}
		
		Pharmacophore pharmacophore = new Pharmacophore();
		
		//PHARMACOPHORE
		JsonNode node = root.path("PHARMACOPHORE");
		if (node.isMissingNode())
		{	
			errors.add("JSON Section \"PHARMACOPHORE\" is missing!");
		}
		else
		{	
			String s = jsonUtils.extractStringKeyword(root, "PHARMACOPHORE", false);
			if (s == null)
				errors.add("Incorrect PHARMACOPHORE " + jsonUtils.getError());
			else
				{
				 //TODO createing pharmacophore from json;
				}
		}
		
		
		
	   	return pharmacophore;
	}
	
	
	
	
	
	
	public void WriteToJSON(Pharmacophore pharmacophore, File jsonFilePath) {
		  
	 
			 BufferedWriter bw  = null;
			 try {
				 FileWriter fw = new FileWriter(jsonFilePath);
				 bw = new BufferedWriter(fw);
				 bw.write(pharmacophore.toJSONKeyWord(""));
			 }  
			 catch (IOException ioe) {
				 ioe.printStackTrace();
			 }
			 finally{ 
				 try{
					 if(bw!=null)
						 bw.close();
				 }catch(Exception ex){
					 System.out.println("Error in closing the BufferedWriter"+ex);
				 }
		 
			 }
	}
}
