package Controller;

import View.Global_View;
import View.Screen;
import View.Keypad;

/**
 *
 * @author Zara Veda
 */
public class Transfer_Controller extends Transaction {
    public static final int CANCELED = 0;
    public Keypad keypad;
    public int recipient;
    public double amount;
    public double accumulation;
    private Global_View global = new Global_View();
    
    public Transfer_Controller(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {
        
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }
    
    @Override
    public void execute() {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      boolean exist, end = false;
      
      accumulation = bankDatabase.getAccumulate(getAccountNumber());
      while(!end) {
        recipient = accountNumberOfRecipient();
        exist = bankDatabase.validateRecipient(recipient);
        
        if(recipient != getAccountNumber()) {
          if(exist) {
            amount = amountOfTransfer();
          
            if(amount == 0) {
                end = true;
                screen.displayMessageLine("\nCanceling transaction...");
            } else {
                bankDatabase.moneyTransfer(getAccountNumber(), recipient, amount);
                screen.displayMessageLine("\nTransaction succeed!");
                end = true;
            }
          } else {
            screen.displayMessageLine("\nPlease enter the right account number!");
          }  
        } else {
            screen.displayMessageLine("Please enter the right account number!");
        }
     }
  }
    
    private double amountOfTransfer() {
        keypad = new Keypad();
        Screen screen = getScreen();
            
        screen.displayMessage("Please enter amount of transfer in CENTS " +
               "or 0 to Cancel : ");
        int input = keypad.getInput();
        
        if(input == CANCELED) {
           return CANCELED;
        } else {
           return (double) input/100;
        }
    }
    
    private int accountNumberOfRecipient() {
        keypad = new Keypad();
        Screen screen = getScreen();
        
        screen.displayMessage("Please enter the recipient account number : ");
        int inputNumber = keypad.getInput();
        
        return inputNumber;
    }
}
