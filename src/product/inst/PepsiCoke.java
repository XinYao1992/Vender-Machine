package product.inst;

import product.abst.Brand;
import product.abst.Drink;

public class PepsiCoke extends Drink{

	public PepsiCoke(double price, String name, String size, Brand brand) {
		super(price, name, size, brand);
	}
	
}
