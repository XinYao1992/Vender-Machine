package factory.product.abst;

import factory.general.Factory;
import product.abst.Brand;
import product.abst.DigitalDevice;

public interface DigitalFactory extends Factory{
	public DigitalDevice createIntance(double price, String name, Brand brand);
}
