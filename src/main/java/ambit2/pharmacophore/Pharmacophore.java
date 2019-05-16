package ambit2.pharmacophore;

import java.util.ArrayList; 
import ambit2.pharmacophore.features.*;

public class Pharmacophore 
{
	ArrayList<IFeature> features = null;
	ArrayList<IFeatureConnection> conections = null; 
	String pharmacophoreName = null;
	
	public Pharmacophore() {
		super();
 
	}
	
	public Pharmacophore(ArrayList<IFeature> features, ArrayList<IFeatureConnection> conections) {
		super();
		this.features = features;
		this.conections = conections;
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
	
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		sb.append(offset +  "\t\"PHARMACOPHORE_NAME\" :" +"\n");
		sb.append(offset +  "\t\t\"FEATURES\" :" +"\n");
		sb.append(offset + "\t\t[" +"\n");
		
		for (int i = 0; i < features.size(); i++) {
			sb.append(offset + features.get(i). toJSONKeyWord("\t\t\t"));
			if(i<features.size()-1) {
			sb.append(",");}
			sb.append(offset  +"\n");
		}
		
		sb.append(offset + "\t\t]" +"\n");
		sb.append(offset + "}");
		return sb.toString();
		
	}
}
