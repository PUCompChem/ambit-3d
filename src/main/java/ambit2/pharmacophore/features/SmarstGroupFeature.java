package ambit2.pharmacophore.features;

import ambit2.smarts.IsomorphismTester;
import ambit2.smarts.SmartsParser;
import ambit2.smarts.groups.GroupMatch;

public class SmarstGroupFeature implements IFeature
{

	String smarts = null;
	private GroupMatch groupMatch = null;
	
	public SmarstGroupFeature(String smarts, 
			SmartsParser parser, 
			IsomorphismTester isoTester) throws Exception
	{
		this.smarts = smarts;
		groupMatch = new GroupMatch(smarts, parser, isoTester);		
	}	
	
	
	public Type getType() {
		return Type.SMARTS_GROUP;
	}

	public String getName() {
		return null;
	}

	public String getInfo() {
		return null;
	}

	public String toJSONKeyWord(String offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
