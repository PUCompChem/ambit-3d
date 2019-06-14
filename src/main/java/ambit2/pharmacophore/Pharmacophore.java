package ambit2.pharmacophore;

 
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.pharmacophore.features.*;

 

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
	
	
	public static Pharmacophore extractPharmacophoreFromJson(JsonNode node, List<String> errors) {	
		
		Pharmacophore pharmacophore = new Pharmacophore();
		if (!node.path("NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				pharmacophore.setName(keyword);
				pharmacophore.FlagName = true;
			}
		}
		if (!node.path("INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}else {
			pharmacophore.setInfo(keyword);
			pharmacophore.FlagInfo = true;
			}
		}
		
		if (!node.path("FEATURES").isMissingNode()) {
			pharmacophore.FlagFeatures = true;
		JsonNode featuresNode = node.path("FEATURES"); 
		for (int i = 0; i < node.size(); i++) {
			JsonNode currentNode = featuresNode.get(i);
			SmartsGroupFeature currentSGF = new SmartsGroupFeature();
			
			currentSGF.extractFromJson(currentNode, errors); 
			pharmacophore.getFeatures().add(currentSGF);
		}
		}
		else {
			errors.add(
					"In Pharmacophore Json the keyword \"FEATURES\" is missing");
		}
		
	
		if (!node.path("FEATURES_CONNECTIONS").isMissingNode()) {
			pharmacophore.FlagFeatureConnections = true;
			JsonNode featuresConnectionsNode = node.path("FEATURES_CONNECTIONS");
			for (int i = 0; i < featuresConnectionsNode.size(); i++) {
				JsonNode currentNode =  featuresConnectionsNode.get(i);
				DistanceFeatureConnection  currentDFC = new DistanceFeatureConnection();
				currentDFC.extractFromJson(currentNode, errors);
				pharmacophore.getConections().add(currentDFC);
			}
		}
		else {
			errors.add("In Pharmacophore Json the keyword \"FEATURES\" is missing");
		}
			 
		return pharmacophore;
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
			for (int i = 0; i < features.size(); i++) {
				 
				sb.append(features.get(i).toJSONKeyWord("\t\t"));
				if(i<features.size()-1) {
					sb.append(",");
					}
				sb.append(offset  +"\n");
			}
			sb.append(offset + "\t]"+"\n");
		 
		}
		
		if(FlagFeatureConnections) {
			if (nFields > 0) {
				sb.append(",\n");
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
	 
