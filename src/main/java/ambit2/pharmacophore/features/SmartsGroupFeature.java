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

 
	 
	public static SmartsGroupFeature extractFromJson(JsonNode node, List<String> errors, String errorPrefix) 
	{
		JsonUtils jsonUtils = new JsonUtils();
		SmartsGroupFeature sgf = new SmartsGroupFeature();
		 
		if (!node.path("NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"NAME", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				sgf.setName(jsonUtils.extractStringKeyword(node, "NAME",false));
				sgf.FlagFeatureName  = true;
				sgf.FlagFieldsUsed = true;
			}
		}
		if (!node.path("SMARTS").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"SMARTS", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				sgf.setSmarts(jsonUtils.extractStringKeyword(node, "SMARTS",false));
				sgf.FlagFeatureSmarts  = true;
				sgf.FlagFieldsUsed = true;
			}
		}
		if (!node.path("INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"INFO", false);
			if (keyword == null) {
				errors.add(jsonUtils.getError());
				}
			else {
				sgf.setInfo(jsonUtils.extractStringKeyword(node,"INFO", false));
				sgf.FlagFeatureInfo  = true;
				sgf.FlagFieldsUsed = true;
			}
		}
		
		
		return sgf;
	}

	public String toJSONKeyWord(String offset) {
		StringBuffer sb = new StringBuffer();
		int nFields = 0;
		 {
		sb.append(offset + "{\n");
		
		
		
		if (nFields > 0) {
			sb.append(",\n");
		}
		sb.append(offset +  "\t\"TYPE\" : SMARTS_GROUP");
	nFields++;
	
	
	
	
		if(FlagFeatureName) {
			
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"NAME\" :" +this.getName());
		nFields++;
		}
		if(FlagFeatureSmarts) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"SMARTS\" :" +this.getSmarts());
		nFields++;
		}
		if(FlagFeatureInfo) {
			if (nFields > 0) {
				sb.append(",\n");
			}
			sb.append(offset +  "\t\"INFO\" :" +this.getInfo());
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
