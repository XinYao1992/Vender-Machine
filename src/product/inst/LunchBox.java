package product.inst;

import product.abst.Brand;
import product.abst.Food;
import product.abst.FoodType;

public class LunchBox extends Food{
	
	public LunchBox(double price, String name, String size, Brand brand, FoodType type) {
		super(price, name, size, brand, type);
	}

}
