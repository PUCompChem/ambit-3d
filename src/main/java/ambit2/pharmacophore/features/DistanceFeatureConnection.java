package ambit2.pharmacophore.features;

public class DistanceFeatureConnection 
{
	IFeature feature0;
	IFeature feature1;
	double distanceLoValue = 0.0;
	double distanceUpValue = 0.0;
	
	
	
	
	public DistanceFeatureConnection(IFeature feature0, IFeature feature1, double distanceLoValue, double distanceUpValue) {
		super();
		this.feature0 = feature0;
		this.feature1 = feature1;
		this.distanceLoValue = distanceLoValue;
		this.distanceUpValue = distanceUpValue;
	}
	
	public IFeature getFeature0() {
		return feature0;
	}
	public void setFeature0(IFeature feature0) {
		this.feature0 = feature0;
	}
	public IFeature getFeature1() {
		return feature1;
	}
	public void setFeature1(IFeature feature1) {
		this.feature1 = feature1;
	}
	public double getDistanceLoValue() {
		return distanceLoValue;
	}
	public void setDistanceLoValue(double distanceLoValue) {
		this.distanceLoValue = distanceLoValue;
	}
	public double getDistanceUpValue() {
		return distanceUpValue;
	}
	public void setDistanceUpValue(double distanceUpValue) {
		this.distanceUpValue = distanceUpValue;
	}
	
	
	public String toJSONKeyWord(String offset) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(offset + "{\n");
		
		
		sb.append(offset + "}");
		return sb.toString();
		
	}
}
