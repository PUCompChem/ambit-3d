package ambit2.pharmacophore;

import java.util.ArrayList; 
import ambit2.pharmacophore.features.*;

public class Pharmacophore {
	ArrayList<FeatureInstance> features;
	ArrayList<FeatureConnection> conections; 
	
	public Pharmacophore(ArrayList<FeatureInstance> features, ArrayList<FeatureConnection> conections) {
		super();
		this.features = features;
		this.conections = conections;
	}
	
	public ArrayList<FeatureInstance> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<FeatureInstance> features) {
		this.features = features;
	}
	public ArrayList<FeatureConnection> getConections() {
		return conections;
	}
	public void setConections(ArrayList<FeatureConnection> conections) {
		this.conections = conections;
	}
	
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		
		
		sb.append(offset + "}");
		return sb.toString();
		
	}
}
