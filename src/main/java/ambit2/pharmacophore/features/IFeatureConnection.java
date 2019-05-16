package ambit2.pharmacophore.features;


public interface IFeatureConnection 
{
	public static enum Type {
		DISTANCE, UNDEFINED;
		
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
	
}
