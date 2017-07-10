package services.abst;

import factory.general.Factory;
import payment.abst.Payment;
import vender.abst.Vender;

public interface VenderServices {
	/**
	 * @param type, factory type
	 * @return specific factory created
	 */
	public Factory createFactory(int type);
	
	/**
	 * @param certain type of vender
	 * @return created specific vender
	 */
	public Vender createVender(int type);
	
	
	/**
	 * @param Payment of user, the vender to be used
	 * @return Successful payment or not
	 */
	public boolean insertPayment(Payment pay, Vender vender);
	
	
	/**
	 * 
	 * @param payment to be authenticated
	 * @return if be authenticated or not.
	 */
	public boolean authenticatePayment(Payment pay);
	
	
	/**
	 * @return read input from user
	 */
	public double readInputFromUser();

	/**
	 * @return start a new purchase
	 */
	public void startPurchase();
	
	/**
	 * @return call for exist current purchase
	 */
	public void finishPurchase();
	
}
