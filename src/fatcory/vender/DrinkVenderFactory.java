package fatcory.vender;

import factory.general.Factory;
import motor.abst.Motor;
import vender.inst.DrinkVender;

public class DrinkVenderFactory implements Factory{
	public DrinkVender createInstance(Motor motor){
		return new DrinkVender(motor);
	}
}
