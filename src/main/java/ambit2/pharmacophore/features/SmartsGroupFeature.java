package ambit2.pharmacophore.features;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import ambit2.helpers3d.json.*;
import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;

public class SmartsGroupFeature implements IFeature
{

	String name = null;
	List<String> smartsList = null;
	String smarts = null;
	int customAtomIndex = 0; 
	String info = null;
	


	FeatureCoordinatesAlgorithm coordinatesAlgorithm = FeatureCoordinatesAlgorithm.AVERAGE;

	private GroupMatch groupMatch = null;
	private List<GroupMatch> groupMatchList = null;
	private Type type = Type.SMARTS_GROUP;

	//JSON flags
	boolean FlagFeatureName = false;
	boolean FlagFeatureSmarts = false;
	boolean FlagFeatureSmartsList = false;
	boolean FlagCustomAtom = false;
	boolean FlagFeatureInfo = false;
	boolean FlagCoordinatesAlgorithm = false;

	
	public SmartsGroupFeature(){
	}

	/*
	public SmartsGroupFeature(String smarts, 
			SmartsParser parser, 
			IsomorphismTester isoTester) throws Exception
	{
		this.smarts = smarts;
		configure(parser, isoTester);		
	}
	*/

	
	 
	public void configure(SmartsParser parser, IsomorphismTester isoTester, List<String> errors, String prefix) throws Exception
	{
		if (smarts != null) {
			groupMatch = new GroupMatch(smarts, parser, isoTester);
			if (!groupMatch.getError().equals(""))
				errors.add(prefix + "SMARTS error: " + groupMatch.getError());
		}
		
		if (smartsList != null)
		{	
			groupMatchList = new ArrayList<GroupMatch>();
			for (int i = 0; i < smartsList.size(); i++)
			{
				//System.out.println("--> " + smartsList.get(i));
				GroupMatch grMat = new GroupMatch(smartsList.get(i), parser, isoTester);
				groupMatchList.add(grMat);
				if (!grMat.getError().equals(""))
					errors.add(prefix + "SMARTS [" + (i+1) + "] error: " + grMat.getError());
			}
		}
		 
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
	
	public List<String> getSmartsList() {
		return smartsList;
	}

	public void setSmartsList(List<String> smartsList) {
		this.smartsList = smartsList;
	}	

	public GroupMatch getGroupMatch() {
		return groupMatch;
	}

	public List<GroupMatch> getGroupMatchList() {
		return groupMatchList;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Type getType() {
		return type;
	}
	
 
	public void setCustomAtomIndex(int customAtomIndex) {
		this.customAtomIndex = customAtomIndex;
	}
	public int getCustomAtomIndex() {
		return customAtomIndex;
	}
	public FeatureCoordinatesAlgorithm getFeatureCoordinatesAlgorithm() { 
		return this.coordinatesAlgorithm;
	}
	public void setFeatureCoordinatesAlgorithm(String word) { 
		try {
			this.coordinatesAlgorithm = FeatureCoordinatesAlgorithm.valueOf(word);
		}
		catch(IllegalArgumentException ex) {

		}
	}




	public static SmartsGroupFeature extractFromJson(JsonNode node, List<String> errors, String errorPrefix) 
	{
		JsonUtils jsonUtils = new JsonUtils();
		SmartsGroupFeature sgf = new SmartsGroupFeature();

		if (!node.path("NAME").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"NAME", false);
			if (keyword == null) {
				errors.add(errorPrefix + jsonUtils.getError());
			}
			else {
				sgf.setName(keyword);
				sgf.FlagFeatureName  = true;				
			}
		}


		if (!node.path("SMARTS").isMissingNode()) {
			JsonNode smartsNode = node.path("SMARTS");
			if(smartsNode.isArray()) {
				sgf.FlagFeatureSmartsList = true;
				sgf.type = Type.SMARTS_GROUP_LIST;
				sgf.smartsList = new ArrayList<String>();
				for (int i = 0; i < smartsNode.size(); i++) {
					if (smartsNode.get(i).isTextual())
					{	
						String keyword = smartsNode.get(i).asText();					
						sgf.smartsList.add(keyword);
					}
					else
						errors.add(errorPrefix + "SMARTS[" + (i+1) + "] is not textual");
				}			
			}
			else {
				String keyword = jsonUtils.extractStringKeyword(node,"SMARTS", false);

				if (keyword == null) {
					errors.add(errorPrefix + jsonUtils.getError());
				}
				else {
					sgf.setSmarts(keyword);
					sgf.FlagFeatureSmarts  = true;				
				}
			}
		}


		if (!node.path("INFO").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"INFO", false);
			if (keyword == null) {
				errors.add(errorPrefix + jsonUtils.getError());
			}
			else {
				sgf.setInfo(keyword);
				sgf.FlagFeatureInfo  = true;				
			}
		}
		if (!node.path("COORDINATES_ALGORITHM").isMissingNode()) {
			String keyword = jsonUtils.extractStringKeyword(node,"COORDINATES_ALGORITHM", false);
			if (keyword == null) {
				errors.add(errorPrefix + jsonUtils.getError());
			}
			else {
				try {
					sgf.coordinatesAlgorithm = FeatureCoordinatesAlgorithm.valueOf(keyword);
				}
				catch(IllegalArgumentException ex) {
					errors.add("wrong coordinates algorithm");
				}
				sgf.FlagCoordinatesAlgorithm  = true;				
			}
		}
		if (!node.path("CUSTOM_ATOM").isMissingNode()) {
			 
			Integer keyword = jsonUtils.extractIntKeyword(node,"CUSTOM_ATOM", false);
			if (keyword == null) {
				errors.add(errorPrefix + jsonUtils.getError());
			}
			else {
				try {
				sgf.setCustomAtomIndex(keyword);
				}
				catch(IllegalArgumentException ex) {
					errors.add("wrong custom atom index");
				}
				sgf.FlagCustomAtom = true;		
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


			if(FlagFeatureSmartsList) {
				if (nFields > 0) {
					sb.append(",\n");
				}
				sb.append(offset +  "\t\"SMARTS\" : [");
				for (int i = 0; i < smartsList.size(); i++) {
					sb.append(smartsList.get(i));
					if(i<smartsList.size()-1) {
						sb.append(",");
					}
				}

				sb.append("]");
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
			
			if(FlagCustomAtom) {
				if (nFields > 0) {
					sb.append(",\n");
				}
				sb.append(offset +  "\t\"CUSTOM_ATOM\" :" +this.getCustomAtomIndex());
				nFields++;
			}
			
			if(FlagCoordinatesAlgorithm) {
				if (nFields > 0) {
					sb.append(",\n");
				}
				sb.append(offset +  "\t\"COORDINATES_AGORITHM\" : " +this.coordinatesAlgorithm);
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
