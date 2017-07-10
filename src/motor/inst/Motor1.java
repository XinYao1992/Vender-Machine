package motor.inst;

import java.util.UUID;

import motor.abst.Motor;

public class Motor1 implements Motor{

	private String id;
	
	public Motor1(){
		this.id = UUID.randomUUID().toString();
	}
	
	@Override
	public boolean start() {
		System.out.println(this.id + " has been started!");
		return true;
	}

	@Override
	public boolean stop() {
		System.out.println(this.id + " has been stopped!");
		return true;
	}

	@Override
	public boolean moveLeft(int d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveRight(int d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveUp(int d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveDown(int d) {
		// TODO Auto-generated method stub
		return false;
	}

}
