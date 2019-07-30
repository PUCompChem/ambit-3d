package ambit2.pharmacophore.features;


public interface IFeature 
{
	public static enum Type {
		SMARTS_GROUP, UNDEFINED;
		
		public static Type fromString(String s) {
			try {
				Type type = Type.valueOf(s);
				return (type);
			} catch (Exception e) {
				return Type.UNDEFINED;
			}
		}
	}	
	
	public static enum FeatureCoordinatesAlgorithm {
		MASS_CENTER, AVERAGE, CUSTOM_ATOM, UNDEFINED;
		
		public static FeatureCoordinatesAlgorithm fromString(String s) {
			try {
				FeatureCoordinatesAlgorithm fca = FeatureCoordinatesAlgorithm.valueOf(s);
				return (fca);
			} catch (Exception e) {
				return FeatureCoordinatesAlgorithm.UNDEFINED;
			}
		}
	}
	
		
	public Type getType();
	public FeatureCoordinatesAlgorithm getFeatureCoordinatesAlgorithm();
	public String getName();
	public String getInfo();
	public String toJSONKeyWord(String offset);
		
	
}
