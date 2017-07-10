package run;

import java.util.Map;

import payment.inst.CreditCard;
import product.abst.Product;
import services.inst.VenderServicesImpt;
import vender.abst.Vender;

public class ApplicationRun {

	public static void main(String[] args) {
		while(true){
			// choose vender
			VenderServicesImpt service = new VenderServicesImpt();
			service.startPurchase();
			int input = -1;
			input = (int)service.readInputFromUser();
			Vender operatedVender = service.createVender(input);
			while(operatedVender == null){
				System.out.println("Invalid input. Try again: ");
				input = (int)service.readInputFromUser();
				operatedVender = service.createVender(input);
			}
			
			// choose product
			boolean continueToPurchase = true;
			while(continueToPurchase){
				Map<Integer, Product> menu = operatedVender.showStock();
				System.out.println("Which one do you want? Choose by number: ");
				int order = (int)service.readInputFromUser();
				while(!menu.containsKey(order)){
					System.out.println("Invalid input. Try again: ");
					order = (int)service.readInputFromUser();
				}
				Product p = menu.get(order);
				System.out.println("How many do you want for it? Please type a number: ");
				int amount = (int)service.readInputFromUser();
				service.purchase(p, amount, operatedVender);// dispensing ...
				System.out.println("Do you want to continue to purchase? Type 1 for yes, 0 for no: ");
				int yesOrNo = (int)service.readInputFromUser();
				while(yesOrNo != 0 && yesOrNo != 1){
					System.out.println("Invalid input. Try again (1 for yes, 0 for no): ");
					yesOrNo = (int)service.readInputFromUser();
				}
				continueToPurchase = (yesOrNo == 1) ? true : false;
			}
			
			// pay
			boolean validation = false;
			while(!validation){
				System.out.println("Please choose following payment approaches: 0 -- CreditCard");
				int payWay = (int)service.readInputFromUser();
				while(payWay != 0){
					System.out.println("Invalid input. Try again. Please choose following payment approaches: 0 -- CreditCard");
					payWay = (int)service.readInputFromUser();
				}
				System.out.println("Please insert your payment. Please type 1 when you are done: ");
				int doneOrNot = (int)service.readInputFromUser();
				while(doneOrNot != 1){
					System.out.println("Please re-insert your payment. Please type 1 when you are done: ");
					doneOrNot = (int)service.readInputFromUser();
				}
				if(!service.insertPayment(new CreditCard(Integer.MAX_VALUE), operatedVender)){
					System.out.println("Payment error! Please choose your payment again.");
				}
				else{
					validation = true;
				}
			}
			
			// print recipt
			System.out.println("Do you want to print your recipt? Please type 1 for yes, 0 for no: ");
			int yesOrNo = (int)service.readInputFromUser();
			while(yesOrNo != 0 && yesOrNo != 1){
				System.out.println("Invalid input. Try again (1 for yes, 0 for no): ");
				yesOrNo = (int)service.readInputFromUser();
			}
			switch(yesOrNo){
				case 1:
					service.printRecipt();
					break;
				case 0:
					break;
				default:
					break;
			}
			
			// finish current purchase
			service.finishPurchase();
			
		}// purchase life-cycle
	}

}
