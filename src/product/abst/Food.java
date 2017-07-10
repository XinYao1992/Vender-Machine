package product.abst;

public class Food extends Product{

	protected String size;
	protected FoodType type;
	public static final String SMALL = "small";
	public static final String MEDIUM = "medium";
	public static final String LARGE = "large";
	
	
	public Food(double price, String name, String size, Brand brand, FoodType type) {
		super(price, name, brand);
		this.size = size;
		this.type = type;
	}
	
	public String getSize(){
		return this.size;
	}
	
	public void setSize(String s){
		this.size = s;
	}
	
	public FoodType getType(){
		return this.type;
	}
	
	public void setType(FoodType type){
		this.type = type;
	}
	
	public String getInfo(){
		return this.name+" "+this.type+" "+this.getBrand()+" "+this.getSize();
	}
}
