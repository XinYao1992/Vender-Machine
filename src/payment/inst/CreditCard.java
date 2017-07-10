package payment.inst;

import payment.abst.Payment;

public class CreditCard implements Payment{

	protected double balanceAvailable; 
	
	public CreditCard(double balanceAvailable){
		this.balanceAvailable = balanceAvailable;
	}
	
	public double getBalance(){
		return this.balanceAvailable;
	}
	
	@Override
	public boolean pay(double fee) {
		if(fee > this.balanceAvailable){
			System.out.println("Not enough balance available. Please try another payment.");
			return false;
		}
		this.balanceAvailable -= fee;
		System.out.println("Payment done! Thank you.");
		return true;
	}

}
