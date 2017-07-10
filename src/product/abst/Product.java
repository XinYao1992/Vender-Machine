package product.abst;

import java.util.UUID;

public class Product {
	
	protected double price;
	protected String name;
	protected String id;
	protected Brand brand;
	
	public Product(double p, String n, Brand b){
		this.price = p;
		this.name = n;
		this.id = UUID.randomUUID().toString();// generate unique id
		this.brand = b;
	}
	
	public String getID(){
		return this.id;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public void setPrice(double p){
		this.price = p;
	}
	
	public Brand getBrand(){
		return this.brand;
	}
	public void setBrand(Brand brand){
		this.brand = brand;
	}
}
