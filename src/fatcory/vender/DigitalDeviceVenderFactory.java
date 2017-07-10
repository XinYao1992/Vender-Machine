package fatcory.vender;

import factory.general.Factory;
import motor.abst.Motor;
import vender.inst.DigitalDeviceVender;

public class DigitalDeviceVenderFactory implements Factory{
	public DigitalDeviceVender createInstance(Motor motor){
		return new DigitalDeviceVender(motor);
	}
}
