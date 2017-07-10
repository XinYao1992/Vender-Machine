package services.inst;

import java.util.ArrayList;
import java.util.Scanner;

import factory.general.Factory;
import fatcory.vender.DigitalDeviceVenderFactory;
import fatcory.vender.DrinkVenderFactory;
import fatcory.vender.FoodVenderFactory;
import motor.inst.Motor1;
import payment.abst.Payment;
import payment.inst.CreditCard;
import product.abst.Brand;
import product.abst.Food;
import product.abst.FoodType;
import product.abst.Product;
import product.inst.CokeCola;
import product.inst.LunchBox;
import product.inst.OreoChocolate;
import product.inst.PepsiCoke;
import product.inst.iPad;
import product.inst.iPhone;
import services.abst.VenderServices;
import vender.abst.Vender;
import vender.inst.DigitalDeviceVender;
import vender.inst.DrinkVender;
import vender.inst.FoodVender;

public class VenderServicesImpt implements VenderServices{

	protected ArrayList<String> recipt;// renew the recipt when a new client come.
	protected double charge;// the total charge for current client
	
	
	public VenderServicesImpt(){
		this.recipt = new ArrayList<String>();
		this.charge = 0;
	}
	
	/**
	 * @return the total charge user should pay
	 */
	public double getCharge(){
		return this.charge;
	}
	
	/**
	 * clean the recipt for every purchase
	 */
	public void cleanRecipt(){
		this.recipt = new ArrayList<String>();
		this.charge = 0;
	}
	
	/**
	 * print the recipt for current purchase
	 */
	public void printRecipt(){
		this.recipt.add("Total: "+this.charge);
		System.out.println(this.recipt);
	}
	
	/**
	 * 
	 * @param p, product to be purchased
	 * @param amount, how many to buy
	 * @param vender, vender to operate
	 * @return
	 */
	public boolean purchase(Product p, int amount, Vender vender){
		if(vender.dispense(p, amount)){
			this.recipt.add(p.getName()+" -- "+p.getPrice()+", "+amount);
			this.charge += p.getPrice() * amount;
			return true;
		}
		return false;
	}
	
	@Override
	public Factory createFactory(int type) {
		switch(type){
			case 1:
				return new DrinkVenderFactory();
			case 2:
				return new FoodVenderFactory();
			case 3:
				return new DigitalDeviceVenderFactory();
			default:
				break;
		}
		return null;
	}
	
	/**
	 * first create corresponding factory
	 * then create specific vender
	 * finally setup vender
	 */
	@Override
	public Vender createVender(int type) {
		Factory factory = createFactory(type);
		switch(type){
			case 1:
				Motor1 motor1 = new Motor1();
				DrinkVender drinkvender = ((DrinkVenderFactory)factory).createInstance(motor1);
				PepsiCoke pepsi = new PepsiCoke(1.25, "Pepsi Coke", PepsiCoke.SMALL, Brand.Pepsi);
				PepsiCoke pepsiL = new PepsiCoke(2.25, "Pepsi Coke", PepsiCoke.LARGE, Brand.Pepsi);
				CokeCola cola = new CokeCola(1.25, "Coke Cola", CokeCola.LARGE, Brand.Cokecola);
				CokeCola colaL = new CokeCola(2.25, "Coke Cola", CokeCola.SMALL, Brand.Cokecola);
				drinkvender.addProducts(pepsi, 15);
				drinkvender.addProducts(pepsiL, 25);
				drinkvender.addProducts(cola, 20);
				drinkvender.addProducts(colaL, 13);
				return drinkvender;
			case 2:
				Motor1 motor2 = new Motor1();
				FoodVender foodvender = ((FoodVenderFactory)factory).createInstance(motor2);
				LunchBox lunchM = new LunchBox(8.99, "Sushi box", LunchBox.MEDIUM, Brand.Sukiyabashi, FoodType.Meal);
				OreoChocolate oreoS = new OreoChocolate(3.99, "Oreo Chocolate Cookie", Food.SMALL, Brand.Oreo, FoodType.Snake);
				OreoChocolate oreoL = new OreoChocolate(6.99, "Oreo Chocolate Cookie", Food.LARGE, Brand.Oreo, FoodType.Snake);
				foodvender.addProducts(lunchM, 5);
				foodvender.addProducts(oreoS, 15);
				foodvender.addProducts(oreoL, 8);
				return foodvender;
			case 3:
				Motor1 motor3 = new Motor1();
				DigitalDeviceVender digitalvender = ((DigitalDeviceVenderFactory)factory).createInstance(motor3);
				iPhone iphone = new iPhone(799, "iphone x", Brand.Apple);
				iPad ipad = new iPad(399, "ipad z", Brand.Apple);
				digitalvender.addProducts(iphone, 3);
				digitalvender.addProducts(ipad, 7);
				return digitalvender;
			default:
		}
		return null;
	}

	@Override
	public boolean insertPayment(Payment pay, Vender vender) {
		// credit card
		if(pay instanceof CreditCard){
			if(authenticatePayment(pay)){
				if(pay.pay(this.charge)){
					vender.putMoney(charge);
					return true;
				}
			}
		}
		// other payment
		// ...
		return false;
	}

	@Override
	public boolean authenticatePayment(Payment pay) {
		System.out.println("Authenticating...");
		System.out.println("Authenticated!");
		return true;
	}

	@SuppressWarnings("resource")
	@Override
	public double readInputFromUser() {
		System.out.print(">");
		Scanner in = new Scanner(System.in);
		double input = in.nextDouble();
		return input;
	}

	@Override
	public void startPurchase() {
		System.out.println("Welcome to our vender service! Please choose vender: 1 -- drink vender"
				+ ", 2 -- food vender" + ", 3 -- digital device vender.");
	}

	@Override
	public void finishPurchase() {
		this.cleanRecipt();
		System.out.println("Thank you. Bye bye.");
	}

}
