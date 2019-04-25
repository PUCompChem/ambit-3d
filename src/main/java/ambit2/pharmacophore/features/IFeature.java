package ambit2.pharmacophore.features;

import java.util.List;

import org.openscience.cdk.interfaces.IAtom;

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
	
	
	
}