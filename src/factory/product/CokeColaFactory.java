package factory.product;

import factory.product.abst.DrinkFactory;
import product.abst.Brand;
import product.abst.Drink;
import product.inst.CokeCola;

public class CokeColaFactory implements DrinkFactory{


	@Override
	public Drink createInstance(double price, String name, String size, Brand brand) {
		return new CokeCola(price, name, size, brand);
	}

}
