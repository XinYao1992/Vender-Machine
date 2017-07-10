package motor.abst;

public interface Motor {
	/* execution */
	public boolean start();
	public boolean stop();
	
	/* control direction */
	public boolean moveLeft(int d);
	public boolean moveRight(int d);
	public boolean moveUp(int d);
	public boolean moveDown(int d);
	
}
