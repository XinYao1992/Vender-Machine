package product.inst;

import product.abst.Brand;
import product.abst.Drink;

public class CokeCola extends Drink{
	
	public CokeCola(double price, String name, String size, Brand brand) {
		super(price, name, size, brand);
	}

}
