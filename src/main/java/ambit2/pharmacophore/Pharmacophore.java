package ambit2.pharmacophore;

 
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.pharmacophore.features.*;

 

public class Pharmacophore 
{
	String pharmacophoreName = null;
	String info = null;
	ArrayList<IFeature> features = new ArrayList<IFeature>();
	ArrayList<IFeatureConnection> connections = new ArrayList<IFeatureConnection>(); 
	
	public boolean FlagPharmacophoreName = false;
	public boolean FlagPharmacophoreInfo = false;
	public boolean FlagPharmacophoreFeatures = false;
	public boolean FlagPharmacophoreFeatureConnections = false;
	static JsonUtils jsonUtils = new JsonUtils();
	public Pharmacophore() {
		super();
 
	}
	
	public Pharmacophore(ArrayList<IFeature> features, ArrayList<IFeatureConnection> conections) {
		super();
		this.features = features;
		this.connections = conections;
	}
	public String getPharmacophoreName() {
		return pharmacophoreName;
	}

	public void setPharmacophoreName(String pharmacophoreName) {
		this.pharmacophoreName = pharmacophoreName;
		FlagPharmacophoreName = true;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
		FlagPharmacophoreInfo = true;
	}
	public ArrayList<IFeature> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<IFeature> features) {
		this.features = features;
		FlagPharmacophoreFeatures = true;
	 
	}
	public ArrayList<IFeatureConnection> getConections() {
		return connections;
	}
	public void setConections(ArrayList<IFeatureConnection> conections) {
		this.connections = conections;
		FlagPharmacophoreFeatureConnections = true;
	}
	
	
	public static Pharmacophore extractPharmacophoreFromJson(JsonNode node, List<String> errors) {	
		
		Pharmacophore pharmacophore = new Pharmacophore();
		if (!node.path("PHARMACOPHORE_NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"PHARMACOPHORE_NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				pharmacophore.setPharmacophoreName(jsonUtils.extractStringKeyword(node, "PHARMACOPHORE_NAME",false));
				pharmacophore.FlagPharmacophoreName = true;
			}
		}
		if (!node.path("PHARMACOPHORE_INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"PHARMACOPHORE_INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}else {
			pharmacophore.setInfo(jsonUtils.extractStringKeyword(node, "PHARMACOPHORE_INFO",false));
			pharmacophore.FlagPharmacophoreInfo = true;
			}
		}
		
		if (!node.path("FEATURES").isMissingNode()) {
			pharmacophore.FlagPharmacophoreFeatures = true;
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
			pharmacophore.FlagPharmacophoreFeatureConnections = true;
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
		if(FlagPharmacophoreName) {
			if (nFields > 0) {
				sb.append(",\n");
			}
		sb.append(offset +  "\t\"PHARMACOPHORE_NAME\" : \"" +this.getPharmacophoreName());
		nFields++;
		}
		
		if(FlagPharmacophoreInfo) {
			if (nFields > 0) {
				sb.append(",\n");
			}
		sb.append(offset +  "\t\"PHARMACOPHORE_INFO\" :" +this.getInfo());
		nFields++;
		}
		if(FlagPharmacophoreFeatures) {
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
		
		if(FlagPharmacophoreFeatureConnections) {
			 
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
	 
