package Controller;

import View.Screen;
import View.Keypad;
import View.Deposit_View;
import View.Global_View;

public class Deposit extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option

   Deposit_View depositView = new Deposit_View();
   Global_View global = new Global_View();
   
   // Deposit constructor
   public Deposit(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);

   }

   // perform transaction
   @Override
   public void execute() {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      depositSlot = new DepositSlot();
      boolean received;
      
      amount = promptForDepositAmount();
      if(amount==0) {
         global.transactionCanceled();
      } else {
          if(amount>0) {
            bankDatabase.credit(getAccountNumber(), amount);
            received = depositSlot.isEnvelopeReceived();
          
            if(received) {
               depositView.envelopeReceived();
            } 
          } else {
               depositView.transactionFailed();
          }
      }
   }

   // prompt user to enter a deposit amount in cents 
   private double promptForDepositAmount() {
      keypad = new Keypad();
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      depositView.envelopeDeposit();
      depositView.inputAmount();
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
} 
