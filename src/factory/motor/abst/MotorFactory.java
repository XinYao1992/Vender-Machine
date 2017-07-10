package factory.motor.abst;

import factory.general.Factory;
import motor.abst.Motor;

public interface MotorFactory extends Factory{
	public Motor createInstance();
}