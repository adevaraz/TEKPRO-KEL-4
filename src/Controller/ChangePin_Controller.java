package Controller;

import View.Screen;
import View.Keypad;
import View.ChangePin_View;
import View.Global_View;
import Model.ChangePin;

/**
 *
 * @author Zara Veda
 */
public class ChangePin_Controller extends Transaction {
//    private int newPin;
//    private Keypad keypad;
//    private int[] theOldPins;
//    public BankDatabase bankDatabse;
    ChangePin changePin;
    ChangePin_View changePin_v = new ChangePin_View();
    Global_View global = new Global_View();
    
    public ChangePin_Controller(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, int[] userOldPins) {
      
      super(userAccountNumber, atmScreen, atmBankDatabase, userOldPins);
      changePin = new ChangePin(userAccountNumber, atmScreen, atmBankDatabase,
              atmKeypad, userOldPins);
    }
    
    @Override
    public void execute() {
      bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      boolean done = false;
      boolean valid, same = true;
      int validPin;
      int i;
      changePin.theOldPins = getOldPins();
      
      //newPin = insertNewPin();
      int oldPin = bankDatabase.getPinNumber(getAccountNumber());
      
      while(!done) {
          int newPin = insertNewPin();
          
         if(newPin == 0) {
             done = true;
             global.transactionCanceled();
         } else {
             while(same) {
                 int j = 0;
                 while(j<4) {
                     if(newPin != theOldPins[j]) {
                         j++;
                         same = false;
                     } else {
                         same = true;
                         break; 
                     }
                 }
                 
                 boolean digitSame = changePin.isNumberSame(newPin);
                 
                 if(newPin != 0) {
                     if(same || newPin == oldPin || digitSame) {
                         changePin_v.errorInputPin();
//                         screen.displayMessageLine("Please enter different pin!");
                         same = true;
                         newPin = insertNewPin();
                     }
                 } else {
                     done = true;
                     global.transactionCanceled();
//                     screen.displayMessageLine("Canceling transaction...");
                     break;
                 }
             }
          
             if(newPin != 0 && (!same && newPin != oldPin)) {
                 bankDatabase.setPinNumber(getAccountNumber(), newPin);
                 validPin = validateNewPin();
                 valid = bankDatabase.authenticateUser(
                         getAccountNumber(), validPin);
              
                 while(!valid) {
                     changePin_v.errorValidationPin();
//                     screen.displayMessageLine("The pin is not match!");
                     validPin = validateNewPin();
                     valid = bankDatabase.authenticateUser(
                             getAccountNumber(), validPin);
                 }
            
                 if(valid) {
                     done = valid;
                     i = 0;
                     while(i<4) {
                         if(theOldPins[i] == 0) {
                             theOldPins[i] = validPin;
                             break;
                         }
                         i++;
                     }
                 }
                 
                 changePin_v.pinChanged();
//                 screen.displayMessageLine("Your new pin has been setted");
             }
         }
      }
    }
    
    private int insertNewPin() {
      changePin.keypad = new Keypad();
      Screen screen = getScreen();
      
      changePin_v.inputPin();
      int theNewPin = changePin.keypad.getInput();
      
      return theNewPin;
    }
    
    private int validateNewPin() {
      changePin.keypad = new Keypad();
      Screen screen = getScreen();
      
      changePin_v.inputValidate();
      int validatePin = changePin.keypad.getInput();
      
      return validatePin;
    }
    
//    private boolean isNumberSame(int newPin) {
//        boolean same = false;
//        
//        String check = Integer.toString(newPin);
//        
//        int i = 0;
//        
//        while(i<4 && !same) {
//            int j = i+ 1;
//            while(j<4 && !same) {
//                if(check.charAt(i) == check.charAt(j)) {
//                    same = true;
//                }
//                j++;
//            }
//            i++;
//        }
//        
//        return same;
//    }
}
