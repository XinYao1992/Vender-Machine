package factory.product;

import factory.product.abst.FoodFactory;
import product.abst.Brand;
import product.abst.Food;
import product.abst.FoodType;
import product.inst.LunchBox;

public class LunboxFactory implements FoodFactory{

	@Override
	public Food createInstance(double price, String name, String size, Brand brand, FoodType type) {
		return new LunchBox(price, name, size, brand, type);
	}

}
