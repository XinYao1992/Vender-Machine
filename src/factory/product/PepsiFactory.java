package factory.product;

import factory.product.abst.DrinkFactory;
import product.abst.Brand;
import product.abst.Drink;
import product.inst.PepsiCoke;

public class PepsiFactory implements DrinkFactory{

	@Override
	public Drink createInstance(double price, String name, String size, Brand brand) {
		return new PepsiCoke(price, name, size, brand);
	}

}
