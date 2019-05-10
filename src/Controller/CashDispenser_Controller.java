package Controller;

import Model.CashDispenser;

public class CashDispenser_Controller {
//   // the default initial number of bills in the cash dispenser
//   private final static int INITIAL_COUNT = 500;
//   private int count; // number of $20 bills remaining
//   
//   // no-argument CashDispenser constructor initializes count to default
//   public CashDispenser_Controller() {
//      count = INITIAL_COUNT; // set count attribute to default
//   }
    
    CashDispenser cashD = new CashDispenser();
    
   // simulates dispensing of specified amount of cash
   public void dispenseCash (int amount) {
      int billsRequired = amount / 20; // number of $20 bills required
      cashD.count -= billsRequired; // update the count of bills
   }
   
   public void addtoDispenser(int money) {
       cashD.addtoDispenser(money);
   }
   
   public int getAmountofDispenser() {
       int money;
       money = cashD.getAmountofDispenser();
       return money;
   }
   
   public boolean isAvailable(int money) {
       return cashD.isSufficientCashAvailable(money);
   }
//   // indicates whether cash dispenser can dispense desired amount
//   public boolean isSufficientCashAvailable(int amount) {
//      int billsRequired = amount / 20; // number of $20 bills required
//
//      if (count >= billsRequired) {
//         return true; // enough bills available
//      }
//      else {
//         return false; // not enough bills available
//      }
//   }
//   
//   public int getAmountofDispenser() {
//       return count*20;
//   }
//   
//   public void addtoDispenser(int amountofMoney) {
//       count += amountofMoney;
//   }
} 