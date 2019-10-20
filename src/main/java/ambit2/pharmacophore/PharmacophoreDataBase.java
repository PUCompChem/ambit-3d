package ambit2.pharmacophore;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ambit2.pharmacophore.features.IFeature;
import ambit2.pharmacophore.features.SmartsGroupFeature;
import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;

public class PharmacophoreDataBase 
{
		
	public List<Pharmacophore> pharmacophores = new ArrayList<Pharmacophore>();
	public List<String> errors = new ArrayList<String>();
	
	
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
		JsonNode pharmacophoresNode = root.path("PHARMACOPHORES"); 
		for (int i = 0; i < pharmacophoresNode.size(); i++) {
			JsonNode currentPharmacophoreNode = pharmacophoresNode.get(i);
			Pharmacophore currentPharmacophore = 
					Pharmacophore.extractPharmacophoreFromJson(currentPharmacophoreNode, errors, 
							"Pharmacophore " + (i+1) + ": ");
			if (currentPharmacophore == null) {
				errors.add(" Unable to read pharmacophore " + (i+1));
			}else {	
				pharmacophores.add(currentPharmacophore);
			}
		}
 	}
	
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "\"PHARMACOPHORES\" :" + "\n");
		sb.append(offset + "\t[" +"\n");
		
		for (int i = 0; i < pharmacophores.size(); i++) {
			sb.append(pharmacophores.get(i).toJSONKeyWord(offset+"\t\t"));
			
			if(i<pharmacophores.size()-1) {
				sb.append(",");
			}
			
			sb.append(offset  +"\n");
		}
		sb.append(offset + "\t]" +"\n");
		
		return sb.toString();
	}
	
	public void configure(SmartsParser parser, IsomorphismTester isoTester) throws Exception
	{
		//TODO
		// (1) iterate all pharmacophores 
		// 		(2) iterate all feature of type SMARTS
		// 			(3) configure groupMatch 
		//			(4) check for group match errors (i.e. SMARTS errors) and register on errors list 
		
		for (int i = 0; i < pharmacophores.size(); i++) {
			Pharmacophore currentPharmacophore = pharmacophores.get(i);
			for (int j = 0; j < currentPharmacophore.features.size(); j++) {
				IFeature currentFeature = currentPharmacophore.features.get(j);
					if(currentFeature.getType().equals(IFeature.Type.SMARTS_GROUP)) {
						SmartsGroupFeature smartsFeature = (SmartsGroupFeature) currentFeature;
						smartsFeature.configure(parser, isoTester);
					}
			}
			
		}
	}
	
	public String getQuickInfo() 
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < pharmacophores.size(); i++)
		{
			Pharmacophore p = pharmacophores.get(i);
			sb.append("# ");
			sb.append((i+1));
			sb.append(" ");
			sb.append(p.name);
			sb.append(" NumFeat=");
			sb.append(p.features.size());
			sb.append(" NumConn=");
			sb.append(p.connections.size());
			sb.append("\n");
		}
		return sb.toString();
	}
}	
