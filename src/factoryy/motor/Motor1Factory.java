package factoryy.motor;

import factory.motor.abst.MotorFactory;
import motor.abst.Motor;
import motor.inst.Motor1;

public class Motor1Factory implements MotorFactory{

	@Override
	public Motor createInstance() {
		return new Motor1();
	}

}
