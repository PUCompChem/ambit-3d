package ambit2.pharmacophore;

import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;

public class PharmacophoreManager 
{
	protected IChemObjectBuilder builder;
	
	public PharmacophoreManager() {
		this.builder = SilentChemObjectBuilder.getInstance();
	}
	
	public PharmacophoreManager(IChemObjectBuilder builder) {
		if (builder == null)
			this.builder = SilentChemObjectBuilder.getInstance();
		else
			this.builder = builder;
	}
}
