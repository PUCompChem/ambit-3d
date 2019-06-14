package ambit2.pharmacophore.features;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;

public class SmartsGroupFeature implements IFeature
{

	
	String name = null;
	String smarts = null;
	String info = null;
	private GroupMatch groupMatch = null; 
	JsonUtils jsonUtils = new JsonUtils();
	public boolean FlagFeatureName;
	public boolean FlagFeatureSmarts;
	public boolean FlagFeatureInfo;
	public boolean FlagFieldsUsed = false;
	/**
	 * Constructor for testing
	 */
	public SmartsGroupFeature(){
	}

	public SmartsGroupFeature(String smarts, 
			SmartsParser parser, 
			IsomorphismTester isoTester) throws Exception
	{
		this.smarts = smarts;
		configure(parser, isoTester);		
	}
	
	public void configure(SmartsParser parser, IsomorphismTester isoTester) throws Exception
	{
		groupMatch = new GroupMatch(smarts, parser, isoTester);		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmarts() {
		return smarts;
	}

	public void setSmarts(String smarts) {
		this.smarts = smarts;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public Type getType() {
		return Type.SMARTS_GROUP;
	}

 
	 
	public IFeature extractFromJson(JsonNode node, List<String> errors, String errorPrefix) {
		
		 
		if (!node.path("FEATURE_NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setName(jsonUtils.extractStringKeyword(node, "FEATURE_NAME",false));
				this.FlagFeatureName  = true;
				this.FlagFieldsUsed = true;
			}
		}
		if (!node.path("FEATURE_SMARTS").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_SMARTS", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setSmarts(jsonUtils.extractStringKeyword(node, "FEATURE_SMARTS",false));
				this.FlagFeatureSmarts  = true;
				this.FlagFieldsUsed = true;
			}
		}
		if (!node.path("FEATURE_INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"FEATURE_INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				this.setName(jsonUtils.extractStringKeyword(node, "FEATURE_INFO",false));
				this.FlagFeatureInfo  = true;
				this.FlagFieldsUsed = true;
			}
		}
		
		
		
		return this;
	}

	public String toJSONKeyWord(String offset) {
		StringBuffer sb = new StringBuffer();
		int nFields = 0;
		 {
		sb.append(offset + "{\n");
		if(FlagFeatureName) {
			
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_NAME\" :" +this.getName());
		nFields++;
		}
		if(FlagFeatureSmarts) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_SMARTS\" :" +this.getSmarts());
		nFields++;
		}
		if(FlagFeatureInfo) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"FEATURE_INFO\" :" +this.getInfo());
		nFields++;
		}
	 
		
		if (nFields > 0) {
			sb.append("\n");
		}
		
		sb.append(offset + "}"); 
	
		return sb.toString();
	}
	}
}
