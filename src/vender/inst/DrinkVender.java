package vender.inst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import motor.abst.Motor;
import product.abst.DigitalDevice;
import product.abst.Drink;
import product.abst.Food;
import product.abst.Product;
import vender.abst.Vender;

public class DrinkVender extends Vender{

	/**
	 * Each drink vender has 10 positions to store product queues;
	 * Each queue of products has the maximum 20 products.
	 * Each queue must have one type of product, and the same product can be stored in different queues.
	 */
	
	private HashMap<Integer, Queue<Drink>> drinkStock;//location, product queue
	public static final int POSITION_MAX = 10;
	public static final int QUEUE_SIZE = 20;
	
	public DrinkVender(Motor motor) {
		super(motor);
		drinkStock = new HashMap<Integer, Queue<Drink>>();
		for(int i = 0; i < POSITION_MAX; i ++){
			drinkStock.put(i, new LinkedList<Drink>());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Map<K, V> getStockMap() {
		return (Map<K, V>) this.drinkStock;
	}

	@Override
	public Map<Integer, Product> showStock() {
		System.out.println("Menu: ");
		Map<Integer, Product> menu = new HashMap<Integer, Product>();
		HashSet<String> set = new HashSet<String>();
		int order = 1;
		for(int loc : drinkStock.keySet()){
			Drink p = drinkStock.get(loc).peek();
			if(p == null || set.contains(p.getInfo())) continue;
			set.add(p.getInfo());
			menu.put(order, p);
			System.out.println(order+" -- "+p.getInfo());
			order ++;
		}
		return menu;
	}

	@Override
	public boolean dispenseDrink(Drink p, int amount) {
		System.out.print("Now, dispensing...");
		int original = amount;
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> needs = new ArrayList<Integer>();
		// checking queues
		for(int loc : drinkStock.keySet()){
			if(drinkStock.get(loc).size() != 0 
					&& drinkStock.get(loc).peek().getInfo().equals(p.getInfo())){
				positions.add(loc);
				if(drinkStock.get(loc).size() >= amount){
					needs.add(amount);
					amount = 0;
				}
				else{
					needs.add(drinkStock.get(loc).size());
					amount -= drinkStock.get(loc).size();
				}
			}
			if(amount == 0) break;
		}
		if(amount > 0){
			System.out.print("Sorry, we don't have "+original+" "+p.getInfo()+". Please try less amount.");
			return false;
		}
		// dispensing from vender
		for(int i = 0; i < positions.size(); i ++){
			int pos = i, need = needs.get(i);
			while(need-- > 0){
				drinkStock.get(pos).poll();
			}
		}
		System.out.print("Finished dispensing "+original+" "+p.getInfo()+"...");
		return true;
	}

	@Override
	public boolean dispenseFood(Food p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean dispenseDevice(DigitalDevice p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean addDrink(Drink p, int amount) {
		System.out.print("Adding product...");
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> needs = new ArrayList<Integer>();
		// check queues
		for(int loc : drinkStock.keySet()){
			int addingAmount = -1;
			if(drinkStock.get(loc).size() == 0){
				if(amount > QUEUE_SIZE){
					addingAmount = QUEUE_SIZE;
					amount -= QUEUE_SIZE;
				}
				else{
					addingAmount = amount;
					amount = 0;
				}
			}
			else if(drinkStock.get(loc).peek().getInfo().equals(p.getInfo())){
				if(drinkStock.get(loc).size() < QUEUE_SIZE){// this queue is not full yet.
					// put all left product into current queue.
					if(QUEUE_SIZE-drinkStock.get(loc).size() >= amount){
						amount = 0;
					    addingAmount = amount;
					}
					// fulfill this queue with product p
					else {
						amount -= QUEUE_SIZE-drinkStock.get(loc).size();
						addingAmount = QUEUE_SIZE-drinkStock.get(loc).size();
					}
				}
			}
			positions.add(loc);
			needs.add(addingAmount);
			if(amount == 0) break;
		}
		if(amount > 0){
			System.out.println("This vender don't have enough space. Please try another one...");
			return false;
		}
		// adding into vender
		for(int i = 0; i < positions.size(); i ++){
			int need = needs.get(i);
			while(need -- > 0){
				drinkStock.get(i).offer(p);
			}
		}
		System.out.println("Finished!");
		return true;
	}

	@Override
	public boolean addFood(Food p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean addDevice(DigitalDevice p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public void checkDrink(Drink p) {
		int amount = 0;
		for(int loc : drinkStock.keySet()){
			if(drinkStock.get(loc).size() != 0 
					&& drinkStock.get(loc).peek().getInfo().equals(p.getInfo())){
				amount += drinkStock.get(loc).size();
			}
		}
		if(amount > 0){
			System.out.println(p.getInfo()+" has "+ amount +" left in this vender.");
			return ;
		}
		System.out.println(p.getInfo()+" has no left. Sorry.");
	}

	@Override
	public void checkFood(Food p) {
		System.out.println("Product type error! Try again.");
	}

	@Override
	public void checkDevice(DigitalDevice p) {
		System.out.println("Product type error! Try again.");
	}

	/**
	 * @param drinkStock, the stock to be setup into
	 */
	public void setVender(HashMap<Integer, Queue<Drink>> drinkStock) {
		this.drinkStock = drinkStock;
	}

}
