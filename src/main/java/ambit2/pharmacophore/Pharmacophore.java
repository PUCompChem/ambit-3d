package ambit2.pharmacophore;

 
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.pharmacophore.features.*;
import ambit2.pharmacophore.features.IFeature.Type;

 

public class Pharmacophore 
{
	String name = null;
	String info = null;
	ArrayList<IFeature> features = new ArrayList<IFeature>();
	ArrayList<IFeatureConnection> connections = new ArrayList<IFeatureConnection>(); 
	
	public boolean FlagName = false;
	public boolean FlagInfo = false;
	public boolean FlagFeatures = false;
	public boolean FlagFeatureConnections = false;
	static JsonUtils jsonUtils = new JsonUtils();
	public Pharmacophore() {
		super();
 
	}
	
	public Pharmacophore(ArrayList<IFeature> features, ArrayList<IFeatureConnection> conections) {
		super();
		this.features = features;
		this.connections = conections;
	}
	public String getName() {
		return name;
	}

	public void setName(String pharmacophoreName) {
		this.name = pharmacophoreName;
		FlagName = true;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
		FlagInfo = true;
	}
	public ArrayList<IFeature> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<IFeature> features) {
		this.features = features;
		FlagFeatures = true;
	 
	}
	public ArrayList<IFeatureConnection> getConections() {
		return connections;
	}
	public void setConections(ArrayList<IFeatureConnection> conections) {
		this.connections = conections;
		FlagFeatureConnections = true;
	}
	
	
	public static Pharmacophore extractPharmacophoreFromJson(JsonNode node, List<String> errors, String errorPrefix) {	
		
		Pharmacophore pharmacophore = new Pharmacophore();
		if (!node.path("NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"NAME", false);
			if (keyword == null) {
				errors.add(errorPrefix + " keyword NAME " + jsonUtils.getError());
				}
			else {
				pharmacophore.setName(keyword);
				pharmacophore.FlagName = true;
			}
		}
		if (!node.path("INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"INFO", false);
			if (keyword == null) {
				errors.add(errorPrefix + jsonUtils.getError());
				}else {
			pharmacophore.setInfo(keyword);
			pharmacophore.FlagInfo = true;
			}
		}
		
		if (!node.path("FEATURES").isMissingNode()) {
			pharmacophore.FlagFeatures = true;
		JsonNode featuresNode = node.path("FEATURES"); 
		
		for (int i = 0; i < featuresNode.size(); i++) {
			JsonNode currentNode = featuresNode.get(i);
			IFeature feat = extractFeatureFromJson (currentNode, errors, i, errorPrefix);
			if (feat == null)
				errors.add(errorPrefix + " Unable to read feature " + (i+1));
			else	
				pharmacophore.getFeatures().add(feat);
		}
		}
		else {
			errors.add(errorPrefix + 
					" In Pharmacophore Json the keyword \"FEATURES\" is missing");
		}
		
	//extract FEATURES_CONNECTIONS
		
		if (!node.path("FEATURES_CONNECTIONS").isMissingNode()) {
			pharmacophore.FlagFeatureConnections = true;
			JsonNode featuresConnectionsNode = node.path("FEATURES_CONNECTIONS");
			for (int i = 0; i < featuresConnectionsNode.size(); i++) {
				JsonNode currentNode =  featuresConnectionsNode.get(i);
				IFeatureConnection  featConnection = extractFeatureConnectionFromJson(currentNode, errors, i, errorPrefix);
				 
				if (featConnection == null)
					errors.add(errorPrefix + " Unable to read feature connection " + (i+1));
				else	
					pharmacophore.getConections().add(featConnection);
			}
		}
			else {
				errors.add(errorPrefix + 
						" In Pharmacophore Json the keyword \"FEATURES_CONNECTIONS\" is missing");
			}		 
		return pharmacophore;
	}
	
	public static IFeature extractFeatureFromJson(JsonNode node, List<String> errors, int featureIndex, String errorPrefix) 
	{	
				
		IFeature.Type t = null;
		
		if (!node.path("TYPE").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"TYPE", false);
			if (keyword == null) {
				errors.add(errorPrefix + " in FEATURES[" + (featureIndex+1) + "] keyword TYPE " + jsonUtils.getError());
				}
			else {
				t = IFeature.Type.fromString(keyword);
				if (t == Type.UNDEFINED)
					errors.add(errorPrefix + " FEATURES[" + (featureIndex+1) + "] keyword TYPE is incorrect " );
			}
		}
		else
		{	
			errors.add(errorPrefix +  "in FEATURES[" + (featureIndex+1) + "] keyword TYPE is missing");
			return null;
		}	
		
		
		switch (t)
		{
		case SMARTS_GROUP:
			
			IFeature currentSGF = SmartsGroupFeature.extractFromJson(node, errors, errorPrefix + " FEATURES [" + (featureIndex+1) + "] "); 			
			return currentSGF;
			
		default:
			return null;
		}
		
	}
	public static IFeatureConnection extractFeatureConnectionFromJson(JsonNode node, 
						List<String> errors, 
						int featureConnectionIndex,
						String errorPrefix) 
	{	
		
		
		IFeatureConnection.Type t = null;
		
		if (!node.path("TYPE").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"TYPE", false);
			if (keyword == null) {
				errors.add(errorPrefix + " in FEATURES_CONNECTIONS[" + (featureConnectionIndex+1) + "] keyword TYPE " + jsonUtils.getError());
				}
			else {
				t = IFeatureConnection.Type.fromString(keyword);
				if (t == IFeatureConnection.Type.UNDEFINED)
					errors.add(errorPrefix + " FEATURES_CONNECTIONS[" + (featureConnectionIndex+1) + "] keyword TYPE is incorrect " );
			}
		}
		else
		{	
			errors.add(errorPrefix + " in FEATURES_CONNECTIONS[" + (featureConnectionIndex+1) + "] keyword TYPE is missing");
			return null;
		}	
		
		
		switch (t)
		{
		case DISTANCE:
			
			IFeatureConnection currentDFC = DistanceFeatureConnection.
				extractFromJson(node, errors, errorPrefix + " FEATURES_CONNECTIONS[" + (featureConnectionIndex+1) + "] "); 			
			return currentDFC;
			
		default:
			return null;
		}
		
	}
	
	/**
	 * Converts the class into json string
	 * @param String offset
	 * @return Converterted pharmacophore to string 
	 */
	public String toJSONKeyWord(String offset) {
		int nFields = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(offset+"{"+"\n");
		if(FlagName) {
			if (nFields > 0) {
				sb.append(",\n");
			}
		sb.append(offset +  "\t\"NAME\" : \"" + name + "\"");
		nFields++;
		}
		
		if(FlagInfo) {
			if (nFields > 0) {
				sb.append(",\n");
			}
		sb.append(offset +  "\t\"INFO\" : \"" + info + "\"");
		nFields++;
		}
		
		if(FlagFeatures) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURES\" :" + "\n");
			sb.append(offset + "\t[" +"\n");
			for (int i = 0; i < features.size(); i++) 
			{	
				sb.append(features.get(i).toJSONKeyWord(offset + "\t\t"));
				if(i<features.size()-1) {
					sb.append(",");
					}
				sb.append(offset  +"\n");
			}
			sb.append(offset + "\t]");
		 
		}
		
		if(FlagFeatureConnections) {
			if (nFields > 0) {
				sb.append(",\n\n");
			}
			 
			sb.append(offset +  "\t\"FEATURES_CONNECTIONS\" :" + "\n");
			sb.append(offset + "\t[" +"\n");
			for (int i = 0; i < connections.size(); i++) {
				sb.append(connections.get(i).toJSONKeyWord("\t\t"));
				if(i<connections.size()-1) {
					sb.append(",");
				}
				sb.append(offset  +"\n");
			}
			sb.append(offset + "\t]" +"\n");
			
			nFields++;
		}
		
		 
		sb.append(offset+"\n"+"}");
 
		return sb.toString();
		
	}

}
	 
