package ambit2.pharmacophore.features;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface IFeatureConnection 
{
	public static enum Type {
		DISTANCE_3D, DISTANCE_2D, UNDEFINED;
		
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
	public IFeature getFeature(int index);
	public String toJSONKeyWord(String offset);
	//public IFeatureConnection extractFromJson(JsonNode node, List<String> errors);
	
}
