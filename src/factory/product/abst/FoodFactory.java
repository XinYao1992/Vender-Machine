package factory.product.abst;

import factory.general.Factory;
import product.abst.Brand;
import product.abst.Food;
import product.abst.FoodType;

public interface FoodFactory extends Factory{
	public Food createInstance(double price, String name, String size, Brand brand, FoodType type);
}
