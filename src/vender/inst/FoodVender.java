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

public class FoodVender extends Vender{

	/**
	 * Each drink vender has 10 positions to store product queues;
	 * Each queue of products has the maximum 20 products.
	 * Each queue must have one type of product, and the same product can be stored in different queues.
	 */
	
	private HashMap<Integer, Queue<Food>> foodStock;//location, product queue
	public static final int POSITION_MAX = 10;
	public static final int QUEUE_SIZE = 20;
	
	public FoodVender(Motor motor) {
		super(motor);
		foodStock = new HashMap<Integer, Queue<Food>>();
		for(int i = 0; i < POSITION_MAX; i ++){
			foodStock.put(i, new LinkedList<Food>());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Map<K, V> getStockMap() {
		return (Map<K, V>) this.foodStock;
	}

	@Override
	public Map<Integer, Product> showStock() {
		System.out.println("Menu: ");
		Map<Integer, Product> menu = new HashMap<Integer, Product>();
		HashSet<String> set = new HashSet<String>();
		int order = 1;
		for(int loc : foodStock.keySet()){
			Food p = foodStock.get(loc).peek();
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
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean dispenseFood(Food p, int amount) {
		System.out.print("Now, dispensing...");
		int original = amount;
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> needs = new ArrayList<Integer>();
		// checking queues
		for(int loc : foodStock.keySet()){
			if(foodStock.get(loc).size() != 0 
					&& foodStock.get(loc).peek().getInfo().equals(p.getInfo())){
				positions.add(loc);
				if(foodStock.get(loc).size() >= amount){
					needs.add(amount);
					amount = 0;
				}
				else{
					needs.add(foodStock.get(loc).size());
					amount -= foodStock.get(loc).size();
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
				foodStock.get(pos).poll();
			}
		}
		System.out.print("Finished dispensing "+original+" "+p.getInfo()+"...");
		return true;
	}

	@Override
	public boolean dispenseDevice(DigitalDevice p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean addDrink(Drink p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public boolean addFood(Food p, int amount) {
		System.out.print("Adding product...");
		ArrayList<Integer> positions = new ArrayList<Integer>();
		ArrayList<Integer> needs = new ArrayList<Integer>();
		// check queues
		for(int loc : foodStock.keySet()){
			int addingAmount = -1;
			if(foodStock.get(loc).size() == 0){
				if(amount > QUEUE_SIZE){
					addingAmount = QUEUE_SIZE;
					amount -= QUEUE_SIZE;
				}
				else{
					addingAmount = amount;
					amount = 0;
				}
			}
			else if(foodStock.get(loc).peek().getInfo().equals(p.getInfo())){
				if(foodStock.get(loc).size() < QUEUE_SIZE){// this queue is not full yet.
					// put all left product into current queue.
					if(QUEUE_SIZE-foodStock.get(loc).size() >= amount){
						amount = 0;
					    addingAmount = amount;
					}
					// fulfill this queue with product p
					else {
						amount -= QUEUE_SIZE-foodStock.get(loc).size();
						addingAmount = QUEUE_SIZE-foodStock.get(loc).size();
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
				foodStock.get(i).offer(p);
			}
		}
		System.out.println("Finished!");
		return true;
	}

	@Override
	public boolean addDevice(DigitalDevice p, int amount) {
		System.out.println("Product type error! Try again.");
		return false;
	}

	@Override
	public void checkDrink(Drink p) {
		System.out.println("Product type error! Try again.");
	}

	@Override
	public void checkFood(Food p) {
		int amount = 0;
		for(int loc : foodStock.keySet()){
			if(foodStock.get(loc).size() != 0 
					&& foodStock.get(loc).peek().getInfo().equals(p.getInfo())){
				amount += foodStock.get(loc).size();
			}
		}
		if(amount > 0){
			System.out.println(p.getInfo()+" has "+ amount +" left in this vender.");
			return ;
		}
		System.out.println(p.getInfo()+" has no left. Sorry.");
	}

	@Override
	public void checkDevice(DigitalDevice p) {
		System.out.println("Product type error! Try again.");
	}

}
