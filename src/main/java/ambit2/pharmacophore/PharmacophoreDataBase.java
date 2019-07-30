package ambit2.pharmacophore;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;

public class PharmacophoreDataBase 
{
		
	public List<Pharmacophore> pharmacophores = new ArrayList<Pharmacophore>();
	
	
	public PharmacophoreDataBase(String jsonFileName) throws Exception
	{
		 loadFromJSON(jsonFileName);
		 
		 //configure TODO
	}

	
	private void loadFromJSON(String jsonFileName) throws FileNotFoundException, IOException, JsonProcessingException 
	{
		FileInputStream fin = new FileInputStream(jsonFileName);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;

		try {
			root = mapper.readTree(fin);
		} catch (JsonProcessingException x) {
			throw x;			
		} catch (IOException x) {
			throw x;
		} finally {
			try {
				fin.close();
			} catch (Exception x) {
			}
		}

		//JsonUtilities jsonUtils = new JsonUtilities();
		
		//TODO fill pharmacophire list
	}	
	
	public void configure(SmartsParser parser, IsomorphismTester isoTester) throws Exception
	{
		//TODO
	
	}
}	
