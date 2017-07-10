package product.abst;

public class DigitalDevice extends Product{
	
	public DigitalDevice(double price, String name, Brand brand) {
		super(price, name, brand);
	}
	
	public String getInfo(){
		return this.name+" "+this.getBrand();
	}
}
