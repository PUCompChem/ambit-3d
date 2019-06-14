package ambit2.pharmacophore.features;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface IFeature 
{
	public static enum Type {
		SMARTS_GROUP, UNDEFINED;
		
		public static Type fromString(String s) {
			try {
				Type mode = Type.valueOf(s);
				return (mode);
			} catch (Exception e) {
				return Type.UNDEFINED;
			}
		}
	}
	
	public Type getType();
	public String getName();
	public String getInfo();
	public String toJSONKeyWord(String offset);
	public IFeature extractFromJson(JsonNode node, List<String> errors, String errorPrefix);
	
	
	
}
