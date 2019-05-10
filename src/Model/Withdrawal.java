/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.CashDispenser_Controller;
import Controller.RangeValidate;
import View.Withdrawal_View;
import View.Global_View;
import View.Keypad;
import View.Screen;

/**
 *
 * @author Zara Veda
 */
public class Withdrawal {
   public int amount; // amount to withdraw
   public Keypad keypad; // reference to keypad
   private CashDispenser_Controller cashDispenser; // reference to cash dispenser
   public int accumulate;
   public Withdrawal_View withdrawalView = new Withdrawal_View();
   public Global_View global = new Global_View();

   // constant corresponding to menu option to cancel
   private final static int OTHER = 6;
   public static final int CANCELED = 7;
      
   private boolean isAmountUnder300(int amount) {
        RangeValidate valid = new RangeValidate(0, 300);
        boolean state = valid.validateRange(amount);
        
        return state;
    }
    
    private boolean isAmountUnder1000(int accumulateToday) {
        RangeValidate valid = new RangeValidate(0, 1000);
        
        return valid.validateRange(accumulateToday);
    }
    
    public boolean isAmountAvailable(int amount, int accumulateToday) {
        return isAmountUnder300(amount) && isAmountUnder1000(accumulateToday);
    }
}
