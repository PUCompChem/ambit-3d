package ambit2.pharmacophore.features;

public class DistanceFeatureConnection implements IFeatureConnection
{
	
	IFeature features[] = null;
	double distanceLoValue = 0.0;
	double distanceUpValue = 0.0;
	
	
	public DistanceFeatureConnection(IFeature feature0, IFeature feature1, double distanceLoValue, double distanceUpValue) 
	{
		features = new IFeature[2];
		this.features[0] = feature0;
		this.features[1] = feature1;
		this.distanceLoValue = distanceLoValue;
		this.distanceUpValue = distanceUpValue;
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

	public Type getType() {
		return Type.DISTANCE;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public IFeature getFeature(int index) {
		if (index < 0 || index >= features.length)
			return null;
		return (features[index]);
	}
}
