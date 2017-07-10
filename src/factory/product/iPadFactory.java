package factory.product;

import factory.product.abst.DigitalFactory;
import product.abst.Brand;
import product.abst.DigitalDevice;
import product.inst.iPad;

public class iPadFactory implements DigitalFactory{

	@Override
	public DigitalDevice createIntance(double price, String name, Brand brand) {
		return new iPad(price, name, brand);
	}

}
