package ambit2.pharmacophore.features;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;

public class DistanceFeatureConnection implements IFeatureConnection
{
	String name = null;
	String info = null;
	
	IFeature features[] = null;
	double distanceLoValue = 0.0;
	double distanceUpValue = 0.0;
	
	JsonUtils jsonUtils = new JsonUtils();
	public boolean FlagFeatureConnectionName = false;
	public boolean FlagFeatureConnectionInfo = false;
	public boolean FlagFeatureConnectionLoValue = false;
	public boolean FlagFeatureConnectionUpValue = false;
	public boolean FlagFieldsUsed = false;
	
	
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
	
	public IFeatureConnection extractFromJson(JsonNode node, List<String> errors) {
		if (!node.path("FEATURE_CONNECTION_NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_CONNECTION_NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setName(jsonUtils.extractStringKeyword(node, "FEATURE_CONNECTION_NAME",false));
				this.FlagFeatureConnectionName  = true;
			}
		}
		if (!node.path("FEATURE_CONNECTION_INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_CONNECTION_INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setInfo(jsonUtils.extractStringKeyword(node, "FEATURE_CONNECTION_INFO",false));
				this.FlagFeatureConnectionInfo = true;
			}
		}
		if (!node.path("FEATURE_CONNECTION_DISTANCE_LOVALUE").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_CONNECTION_DISTANCE_LOVALUE", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setDistanceLoValue(jsonUtils.extractDoubleKeyword(node, "FEATURE_CONNECTION_DISTANCE_LOVALUE",false));
				this.FlagFeatureConnectionLoValue = true;
			}
		}
		
		if (!node.path("FEATURE_CONNECTION_DISTANCE_UPVALUE").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_CONNECTION_DISTANCE_UPVALUE", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setDistanceUpValue(jsonUtils.extractDoubleKeyword(node, "FEATURE_CONNECTION_DISTANCE_UPVALUE",false));
				this.FlagFeatureConnectionUpValue = true;
			}
		}
		
		
		return this;
	}
	public String toJSONKeyWord(String offset) {
		int nFields = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		if(FlagFeatureConnectionName) {
			if (nFields > 0) {
				sb.append(",\n");
			}
		sb.append(offset +  "\t\"FEATURE_CONNECTION_NAME\" :" +this.getName());
		nFields++;
		}
		if(FlagFeatureConnectionInfo) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_CONNECTION_INFO\" :" +this.getInfo());
		nFields++;
		}
		if(FlagFeatureConnectionLoValue) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_CONNECTION_DISTANCE_LOVALUE\" :" +this.getDistanceLoValue());
		nFields++;
		}
		if(FlagFeatureConnectionUpValue) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_CONNECTION_DISTANCE_UPVALUE\" :" +this.getDistanceUpValue());
		nFields++;
		}
		if (nFields > 0) {
			sb.append("\n");
		}
		sb.append(offset + "}");
		return sb.toString();
	}
	
	
}
