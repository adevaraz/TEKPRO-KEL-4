package Controller;

import View.Screen;

public abstract class Transaction {
   private int accountNumber; // indicates account involved
   private Screen screen; // ATM screen
   BankDatabase bankDatabase; // account database
   CashDispenser_Controller cashDispenser;
   int[] theOldPins;
   int accumulate;
   
   public Transaction(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {

      accountNumber = userAccountNumber;
      screen = atmScreen;
      bankDatabase = atmBankDatabase;
   }
   
   public Transaction(Screen atmScreen, CashDispenser_Controller atmCashDispenser) {
       screen = atmScreen;
       cashDispenser = atmCashDispenser;
   }
   
   public Transaction(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, CashDispenser_Controller atmCashDispenser, int theAccumulate) {
       
      accountNumber = userAccountNumber;
      screen = atmScreen;
      bankDatabase = atmBankDatabase;
      cashDispenser = atmCashDispenser;
      accumulate = theAccumulate;
   }
   
   public Transaction(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, int[] userOldPins) {

      accountNumber = userAccountNumber;
      screen = atmScreen;
      bankDatabase = atmBankDatabase;
      theOldPins = userOldPins;
   }

   // return account number 
   public int getAccountNumber() {
      return accountNumber; 
   } 

   // return reference to screen
   public Screen getScreen() {
      return screen;
   } 

   // return reference to bank database
   public BankDatabase getBankDatabase() {
      return bankDatabase;
   } 
   
   public CashDispenser_Controller getCashDispenser() {
       return cashDispenser;
   }
   
   public int[] getOldPins() {
       return theOldPins;
   }
   
   // perform the transaction (overridden by each subclass)
   abstract public void execute();
} 