package ambit2.pharmacophore.features;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.jsonUtilities.*;

public class DistanceFeatureConnection implements IFeatureConnection
{
	String name = null;
	String info = null;
	
	IFeature features[] = null;
	double distanceLoValue = 0.0;
	double distanceUpValue = 0.0;
	

	
	public DistanceFeatureConnection(IFeature feature0, IFeature feature1, double distanceLoValue, double distanceUpValue) 
	{
		features = new IFeature[2];
		this.features[0] = feature0;
		this.features[1] = feature1;
		this.distanceLoValue = distanceLoValue;
		this.distanceUpValue = distanceUpValue;
	}
	public DistanceFeatureConnection() 
	{
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public double getDistanceLoValue() {
		return distanceLoValue;
	}
	public void setDistanceLoValue(double distanceLoValue) {
		this.distanceLoValue = distanceLoValue;
	}
	public double getDistanceUpValue() {
		return distanceUpValue;
	}
	public void setDistanceUpValue(double distanceUpValue) {
		this.distanceUpValue = distanceUpValue;
	}
	
	


	public Type getType() {
		return Type.DISTANCE;
	}

	 
	public IFeature getFeature(int index) {
		if (index < 0 || index >= features.length)
			return null;
		return (features[index]);
	}

	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		
		
		sb.append(offset + "}");
		return sb.toString();
	}
	
	public IFeatureConnection extractFromJson(JsonNode node) {
		DistanceFeatureConnection dfc = new DistanceFeatureConnection();
		dfc.setName(JsonUtils.extractStringKeyword(node, "FEATURE_CONNECTION_NAME",false));
		dfc.setInfo(JsonUtils.extractStringKeyword(node, "FEATURE_CONNECTION_INFO",false));
		dfc.setDistanceLoValue(JsonUtils.extractDoubleKeyword(node, "FEATURE_CONNECTION_DISTANCE_LOVALUE",false));
		dfc.setDistanceUpValue(JsonUtils.extractDoubleKeyword(node, "FEATURE_CONNECTION_DISTANCE_UPVALUE",false));
		return dfc;
	}
}
