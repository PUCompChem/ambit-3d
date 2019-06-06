package ambit2.pharmacophore;

 
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.pharmacophore.features.*;
 

public class Pharmacophore 
{
	String pharmacophoreName = null;
	String info = null;
	ArrayList<IFeature> features = new ArrayList<IFeature>();
	ArrayList<IFeatureConnection> connections = new ArrayList<IFeatureConnection>(); 
		
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
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public ArrayList<IFeature> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<IFeature> features) {
		this.features = features;
	}
	public ArrayList<IFeatureConnection> getConections() {
		return connections;
	}
	public void setConections(ArrayList<IFeatureConnection> conections) {
		this.connections = conections;
	}
	
	/**
	 * Converts the class into json string
	 * @param String offset
	 * @return Converterted pharmacophore to string 
	 */
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset+"{"+"\n");
		sb.append(offset +  "\t\"PHARMACOPHORE_NAME\" :" +this.getPharmacophoreName()+","+"\n");
		sb.append(offset +  "\t\"PHARMACOPHORE_INFO\" :" +this.getPharmacophoreName()+","+"\n");
		sb.append(offset +  "\t\"FEATURES\" :" + "\n");
		sb.append(offset + "\t[" +"\n");
		
		for (int i = 0; i < features.size(); i++) {
			sb.append(features.get(i).toJSONKeyWord("\t\t\t"));
			if(i<features.size()-1) {
			sb.append(",");}
			sb.append(offset  +"\n");
		}
		sb.append(offset + "\t]"+"," +"\n");
		
		sb.append(offset +  "\t\"FEATURES_CONNECTIONS\" :" + "\n");
		sb.append(offset + "\t[" +"\n");
		
		for (int i = 0; i < connections.size(); i++) {
			sb.append(connections.get(i).toJSONKeyWord("\t\t\t"));
			if(i<connections.size()-1) {
			sb.append(",");
			}
			sb.append(offset  +"\n");
		}
		sb.append(offset + "\t]" +"\n");
		
		sb.append(offset+"}"+"\n");
	 
		return sb.toString();
		
	}

	public static Pharmacophore extractPharmacophoreFromJson(JsonNode node) {		 
		Pharmacophore pharmacophore = new Pharmacophore();
		pharmacophore.setPharmacophoreName(JsonUtils.extractStringKeyword(node, "PHARMACOPHORE_NAME",false));
		pharmacophore.setInfo(JsonUtils.extractStringKeyword(node, "PHARMACOPHORE_INFO",false));
		
		JsonNode featuresNode = node.path("FEATURES"); 
		for (int i = 0; i < node.size(); i++) {
			JsonNode currentNode = featuresNode.get(i);
			SmartsGroupFeature currentSGF = new SmartsGroupFeature();
			currentSGF.setName(JsonUtils.extractStringKeyword(currentNode, "FEATURE_NAME",false));
			currentSGF.setInfo(JsonUtils.extractStringKeyword(currentNode, "FEATURE_INFO",false));
			currentSGF.setSmarts(JsonUtils.extractStringKeyword(currentNode, "FEATURE_SMARTS",false));
			pharmacophore.getFeatures().add(currentSGF);
		}
			
		JsonNode featuresConnectionsNode = node.path("FEATURES_CONNECTIONS");
		for (int i = 0; i < featuresConnectionsNode.size(); i++) {
			JsonNode currentNode =  featuresConnectionsNode.get(i);
			DistanceFeatureConnection  currentDFC = new DistanceFeatureConnection();
			currentDFC.setName(JsonUtils.extractStringKeyword(currentNode, "FEATURE_CONNECTION_NAME",false));
			currentDFC.setInfo(JsonUtils.extractStringKeyword(currentNode, "FEATURE_CONNECTION_INFO",false));
			currentDFC.setDistanceLoValue(JsonUtils.extractDoubleKeyword(currentNode, "FEATURE_CONNECTION_DISTANCE_LOVALUE",false));
			currentDFC.setDistanceUpValue(JsonUtils.extractDoubleKeyword(currentNode, "FEATURE_CONNECTION_DISTANCE_UPVALUE",false));
			pharmacophore.getConections().add(currentDFC);
		}
		 
		
		 
		
	 
		 
		return pharmacophore;
	}
}
	 
