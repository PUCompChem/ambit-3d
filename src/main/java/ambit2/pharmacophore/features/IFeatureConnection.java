package ambit2.pharmacophore.features;

import ambit2.pharmacophore.features.IFeature.Type;

public interface IFeatureConnection 
{
	public Type getType();
	public String getName();
	public String getInfo();
	public IFeature getFeature(int index);
	public String toJSONKeyWord(String offset);
	
}
