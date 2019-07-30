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
	
	static JsonUtils jsonUtils = new JsonUtils();
	public boolean FlagName = false;
	public boolean FlagInfo = false;
	public boolean FlagDistanceLoValue = false;
	public boolean FlagDistanceUpValue = false;
	//public boolean FlagFieldsUsed = false;
	
	
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
	
	public static IFeatureConnection extractFromJson(JsonNode node, List<String> errors, String errorPrefix) {
		DistanceFeatureConnection currentConnection = new DistanceFeatureConnection();
		if (!node.path("NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setName(keyword);
				currentConnection.FlagName  = true;
			}
		}
		if (!node.path("INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setInfo(keyword);
				currentConnection.FlagInfo = true;
			}
		}
		if (!node.path("FEATURE_1").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_1", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setInfo(keyword);
				currentConnection.FlagInfo = true;
			}
		}
		
		if (!node.path("FEATURE_2").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_2", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setInfo(keyword);
				currentConnection.FlagInfo = true;
			}
		}
		
		
		
		
		
		if (!node.path("DISTANCE_LO_VALUE").isMissingNode()) {
			Double val = jsonUtils.extractDoubleKeyword(node, "DISTANCE_LO_VALUE", false);
			if (val == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setDistanceLoValue(val);
				currentConnection.FlagDistanceLoValue = true;
			}
		}
		else
			errors.add("Keyword DISTANCE_LO_VALUE is missing!");
		
		
		if (!node.path("DISTANCE_UP_VALUE").isMissingNode()) {
			Double val = jsonUtils.extractDoubleKeyword(node,"DISTANCE_UP_VALUE", false);
			if (val == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				currentConnection.setDistanceUpValue(val);
				currentConnection.FlagDistanceUpValue = true;
			}
		}else
			errors.add("Keyword DISTANCE_UP_VALUE is missing!");
		
		
		return currentConnection;
	}
	public String toJSONKeyWord(String offset) {
		int nFields = 0;
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		
		
		if (nFields > 0) {
			sb.append(",\n");
		}
		sb.append(offset +  "\t\"TYPE\" : DISTANCE");
		nFields++;
	
		
		
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
		if(FlagDistanceLoValue) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"DISTANCE_LO_VALUE\" :" +this.getDistanceLoValue());
			nFields++;
		}
		if(FlagDistanceUpValue) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"DISTANCE_UP_VALUE\" :" +this.getDistanceUpValue());
			nFields++;
		}
		
		
	
		
		if (nFields > 0) {
			sb.append("\n");
		}
		sb.append(offset + "}");
		return sb.toString();
	}
	
	
}
