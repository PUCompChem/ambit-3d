package ambit2.pharmacophore.features;

 
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
	
	
	
}
