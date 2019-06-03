package ambit2.pharmacophore.features;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;
import ambit2.helpers3d.jsonUtilities.*;

public class SmartsGroupFeature implements IFeature
{

	
	String name = null;
	String smarts = null;
	String info = null;
	private GroupMatch groupMatch = null; 
	
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

 

	 
	

	public String toJSONKeyWord(String offset) {
		// TODO Auto-generated method stub
		return offset + "Smarts group feature TEST";
	}

	public IFeature extractFromJson(JsonNode node) {
		SmartsGroupFeature sgf = new SmartsGroupFeature();
		sgf.setName(JsonUtils.extractStringKeyword(node, "FEATURE_NAME",false));
		sgf.setSmarts(JsonUtils.extractStringKeyword(node, "FEATURE_SMARTS",false));
		sgf.setInfo(JsonUtils.extractStringKeyword(node, "FEATURE_INFO",false));
		return sgf;
	}

}
