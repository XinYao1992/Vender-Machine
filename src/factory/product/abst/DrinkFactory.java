package factory.product.abst;

import factory.general.Factory;
import product.abst.Brand;
import product.abst.Drink;

public interface DrinkFactory extends Factory{
	public Drink createInstance(double price, String name, String size, Brand brand);
}
