package fatcory.vender;

import factory.general.Factory;
import motor.abst.Motor;
import vender.inst.FoodVender;

public class FoodVenderFactory implements Factory{
	public FoodVender createInstance(Motor motor){
		return new FoodVender(motor);
	}
}
