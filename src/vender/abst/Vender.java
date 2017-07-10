package vender.abst;

import java.util.Map;

import motor.abst.Motor;
import product.abst.DigitalDevice;
import product.abst.Drink;
import product.abst.Food;
import product.abst.Product;

public abstract class Vender {
	protected Motor motor;
	protected double money;// the money this vender stores
	
	public Vender(Motor motor){
		this.motor = motor;
		this.money = 0;
	}
	
	public double showMoney(){
		return this.money;
	}
	
	public void putMoney(double m){
		this.money += m;
	}
	
	public void reduceMoney(double m){
		this.money -= m;
	}
	
	public Motor getMotor(){
		return this.motor;
	}
	
	public void setMotor(Motor m){
		this.motor = m;
	}
	
	// get a map which maps product id to location
	public abstract <K, V> Map<K, V> getStockMap();
	
	// show all product to client in this vender
	public abstract Map<Integer, Product> showStock();
	
	// dispense different type of products
	public boolean dispense(Product p, int amount){
		if(p instanceof Drink){
			if(dispenseDrink((Drink)p, amount)){
				// ..
			}
			else return false;
		}
		else if(p instanceof Food){
			if(dispenseFood((Food) p, amount)){
				// ..
			}
			else return false;
		}
		else if(p instanceof DigitalDevice){
			if(dispenseDevice((DigitalDevice) p, amount)){
				// ..
			}
			else return false;
		}
		// dispense other type of product
		// ....
		else {
			
		}
		return true;
	}
	public abstract boolean dispenseDrink(Drink p, int amount);
	public abstract boolean dispenseFood(Food p, int amount);
	public abstract boolean dispenseDevice(DigitalDevice p, int amount);
	
	// add new products into vender
	public boolean addProducts(Product p, int amount){
		if(p instanceof Drink){
			addDrink((Drink)p, amount);
		}
		else if(p instanceof Food){
			addFood((Food) p, amount);
		}
		else if(p instanceof DigitalDevice){
			addDevice((DigitalDevice) p, amount);
		}
		// add other type of products
		// ...
		else {
			
		}
		return true;
	}
	public abstract boolean addDrink(Drink p, int amount);
	public abstract boolean addFood(Food p, int amount);
	public abstract boolean addDevice(DigitalDevice p, int amount);
	
	// check certain product information
	public void checkProduct(Product p){
		if(p instanceof Drink){
			checkDrink((Drink) p);
		}
		else if(p instanceof Food){
			checkFood((Food) p);
		}
		else if(p instanceof DigitalDevice){
			checkDevice((DigitalDevice) p);
		}
		// check other type of product
		// ....
		else {
			
		}
	}
	public abstract void checkDrink(Drink p);
	public abstract void checkFood(Food p);
	public abstract void checkDevice(DigitalDevice p);
	
	
}
