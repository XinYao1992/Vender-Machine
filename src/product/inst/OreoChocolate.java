package product.inst;

import product.abst.Brand;
import product.abst.Food;
import product.abst.FoodType;

public class OreoChocolate extends Food{
	
	public OreoChocolate(double price, String name, String size, Brand brand, FoodType type) {
		super(price, name, size, brand, type);
	}
	
}
