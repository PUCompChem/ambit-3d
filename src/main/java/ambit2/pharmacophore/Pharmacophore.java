package ambit2.pharmacophore;

 
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import ambit2.pharmacophore.features.*;
import jsonUtilities.JsonUtils;
 

public class Pharmacophore 
{
	String pharmacophoreName = null;
	String info = null;
	ArrayList<IFeature> features = null;
	ArrayList<IFeatureConnection> conections = null; 
		
	public Pharmacophore() {
		super();
 
	}
	
	public Pharmacophore(ArrayList<IFeature> features, ArrayList<IFeatureConnection> conections) {
		super();
		this.features = features;
		this.conections = conections;
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
		return conections;
	}
	public void setConections(ArrayList<IFeatureConnection> conections) {
		this.conections = conections;
	}
	
	/**
	 * Converts the class into json string
	 * @param String offset
	 * @return Converterted pharmacophore to string 
	 */
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		sb.append(offset +  "\t\"PHARMACOPHORE_NAME\" :");
		sb.append(offset +  "\t\"PHARMACOPHORE_NAME\" :" +this.getPharmacophoreName());
		sb.append(offset +  "\t\"PHARMACOPHORE_INFO\" :" +this.getPharmacophoreName());
		sb.append(offset +  "\t\"FEATURES\" :" +"\n");
		sb.append(offset + "\t[" +"\n");
		
		for (int i = 0; i < features.size(); i++) {
			sb.append(features.get(i).toJSONKeyWord("\t\t\t"));
			if(i<features.size()-1) {
			sb.append(",");}
			sb.append(offset  +"\n");
		}
		
		sb.append(offset + "\t]" +"\n");
		sb.append(offset + "}");
		return sb.toString();
		
	}

	public Pharmacophore extractPharmacophoreFromJson(JsonNode node) {		 
		Pharmacophore pharmacophore = new Pharmacophore();
		pharmacophore.setPharmacophoreName(JsonUtils.extractStringKeyword(node, "PHARMACOPHORE_NAME",false));
		pharmacophore.setInfo(JsonUtils.extractStringKeyword(node, "PHARMACOPHORE_INFO",false));
		return pharmacophore;
	}
	 
}
