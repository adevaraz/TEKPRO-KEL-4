package Controller;

// Withdrawal.java

import View.Screen;
import View.Keypad;
import View.Global_View;
import View.Withdrawal_View;
import Model.Withdrawal;

// Represents a withdrawal ATM transaction

public class Withdrawal_Controller extends Transaction {
   Global_View global = new Global_View();
   Withdrawal_View withdrawalView = new Withdrawal_View();
   Withdrawal withdrawal = new Withdrawal();
   
   
   // Withdrawal constructor
   public Withdrawal_Controller(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser_Controller atmCashDispenser, int theAccumulate) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase, atmCashDispenser, theAccumulate);
      
   }

   // perform transaction
   @Override
   public void execute() {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      cashDispenser = getCashDispenser();
      withdrawal.accumulate = bankDatabase.getAccumulate(getAccountNumber());
      
      withdrawal.amount = displayMenuOfAmounts();
      
      if(withdrawal.amount == 0) {
        global.transactionCanceled();
      }
      else {
        if(cashDispenser.isAvailable(withdrawal.amount)) {
            cashDispenser.dispenseCash(withdrawal.amount);
            withdrawal.accumulate += withdrawal.amount;
            bankDatabase.setAccumulate(getAccountNumber(), withdrawal.amount);
            bankDatabase.debit(getAccountNumber(), withdrawal.amount); 
        } else {
            withdrawalView.errorNotEnoughMoney();
        }
      }
   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
//   private int displayMenuOfAmounts() {
//      int userChoice = 0; // local variable to store return value
//      keypad = new Keypad();
//      
//      Screen screen = getScreen(); // get screen reference
//      
//      // array of amounts to correspond to menu numbers
//      int[] amounts = {0, 20, 40, 60, 100, 200};
//
//      // loop while no valid choice has been made
//      while (userChoice == 0) {
//         // display the withdrawal menu
//         screen.displayMessageLine("\nWithdrawal Menu:");
//         screen.displayMessageLine("1 - $20");
//         screen.displayMessageLine("2 - $40");
//         screen.displayMessageLine("3 - $60");
//         screen.displayMessageLine("4 - $100");
//         screen.displayMessageLine("5 - $200");
//         screen.displayMessageLine("6 - Other");
//         screen.displayMessageLine("7 - Cancel transaction");
//         screen.displayMessage("Choose a withdrawal amount: ");
//
//         int input = keypad.getInput(); // get user input through keypad
//
//         // determine how to proceed based on the input value
//         switch (input) {
//            case 1: // if the user chose a withdrawal amount 
//            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
//            case 3: // corresponding amount from amounts array
//            case 4:
//            case 5:
//                userChoice = amounts[input]; // save user's choice
//               break; 
//            case 6: 
//                userChoice = otherAmounts();
//               break;
//            case CANCELED: // the user chose to cancel
//               userChoice = CANCELED; // save user's choice
//               break;
//            default: // the user did not enter a value from 1-6
//               screen.displayMessageLine(
//                  "\nInvalid selection. Try again.");
//         } 
//      } 
//
//      return userChoice; // return withdrawal amount or CANCELED
//   }

    private int otherAmounts() {
        Screen screen = getScreen();
        Keypad keypad = new Keypad();
        BankDatabase bankDatabase = getBankDatabase();
        boolean end = false;
        int input = 0;
        
        while(!end) {
            screen.displayMessage("Enter amount of withdrawal : ");

            input = keypad.getInput();
            if(withdrawal.isAmountAvailable(input, withdrawal.accumulate+input)) {
                end = true;
            } else {
                screen.displayMessageLine("Please enter the right amount!");
            }
        }
        return input;
    }
    
//    private boolean isAmountUnder300(int amount) {
//        RangeValidate valid = new RangeValidate(0, 300);
//        boolean state = valid.validateRange(amount);
//        
//        return state;
//    }
//    
//    private boolean isAmountUnder1000(int accumulateToday) {
//        RangeValidate valid = new RangeValidate(0, 1000);
//        
//        return valid.validateRange(accumulateToday);
//    }
//    
//    private boolean isAmountAvailable(int amount, int accumulateToday) {
//        return isAmountUnder300(amount) && isAmountUnder1000(accumulateToday);
//    }

    public int displayMenuOfAmounts() {
        int userChoice = 0; // local variable to store return value
        Keypad keypad = new Keypad();
        Screen screen = getScreen(); // get screen reference
        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};
        // loop while no valid choice has been made
        
        while (userChoice == 0) {
            // display the withdrawal menu
            withdrawalView.withdrawalMenu();
            
            int input = keypad.getInput(); // get user input through keypad
            // determine how to proceed based on the input value
            switch (input) {
                case 1: // if the user chose a withdrawal amount
                case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:
                    userChoice = amounts[input]; // save user's choice
                    break;
                case 6:
                    userChoice = otherAmounts();
                    break;
                case 7:
                    // the user chose to cancel
                    userChoice = 7; // save user's choice
                    break;
                default:
                    // the user did not enter a value from 1-6
                    global.invalidSelection();
            }
        }
        return userChoice; // return withdrawal amount or CANCELED
    }
} 