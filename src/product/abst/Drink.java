package product.abst;

public class Drink extends Product{

	protected String size;
	public static final String SMALL = "small";
	public static final String LARGE = "large";
	
	public Drink(double price, String name, String s, Brand brand) {
		super(price, name, brand);
		this.size = s;
	}
	
	public String getSize(){
		return this.size;
	}
	
	public void setSize(String s){
		this.size = s;
	}
	
	public String getInfo(){
		return this.name+" "+this.getBrand()+" "+this.getSize();
	}

}
