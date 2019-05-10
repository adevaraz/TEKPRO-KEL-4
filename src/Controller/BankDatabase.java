package Controller;

import Model.Account;
import java.util.ArrayList;

public class BankDatabase {
   private ArrayList<Account> accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new ArrayList<Account>(); // just 2 accounts for testing
      accounts.add(new Account(1234, 4321, true, 1000.0, 1200.0, 1, 0, false));
      accounts.add(new Account(8765, 5678, true, 1000.0, 1200.0, 0, 0, false));
      accounts.add(new Account(1111, 2345, true, 1500.0, 1700.0, 0, 0, false));
      
//      accounts[0] = new Account(1234, 4321, true, 1000.0, 1200.0, 1, 0, false); //1 is admin
//      accounts[1] = new Account(8765, 5678, true, 1000.0, 1200.0, 0, 0, false); //0 is user
//      accounts[2] = new Account(1111, 2345, true, 1500.0, 1700.0, 0, 0, false);
   }
   
   private Account getAccount(int accountNumber) {
       int i = 0;
       
       for (Account account : accounts) {
           if (accountNumber == account.getAccountNumber()) {
               return account;
           }
       }
//       while(i<3) {
//           if(accountNumber != this.accounts[i].getAccountNumber()) {
//               i++;
//           } else {
//              return accounts[i];
//           }
//       }
       return null; // if no matching account was found, return null
   } 

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   }
   
   public boolean validateRecipient(int userAccountNumber) {
      Account userAccount = getAccount(userAccountNumber);
      
       return userAccount != null;
   }
   
   public boolean validateBlockedUser(int userAccountNumber) {
       Account userAccount = getAccount(userAccountNumber);
       
       return userAccount != null;
   }
   
   public int getPinNumber(int userAccountNumber) {
      return getAccount(userAccountNumber).getPinNumber();
   }
   
   public void setPinNumber(int userAccountNumber, int newPin) {
       getAccount(userAccountNumber).setPinNumber(newPin);
   }

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   }
   
   public boolean getStatus(int userAccountNumber) {
       return getAccount(userAccountNumber).getStatus();
   }
   
   public void setStatus(int userAccountNumber, boolean newStatus) {
       getAccount(userAccountNumber).setStatus(newStatus);
   }
   
   public void moneyTransfer(int sender, int recipient, double amount) {
       getAccount(sender).minusTransfer(amount);
       getAccount(recipient).plusTransfer(amount);
   }
   
   public int getRole(int userAccountNumber) {
       return getAccount(userAccountNumber).getRole();
   }
   
   public int getAccumulate(int userAccountNumber) {
       return getAccount(userAccountNumber).getAccumulate();
   }
   
   public void setAccumulate(int userAccountNumber, int amount) {
       getAccount(userAccountNumber).setAccumulate(amount);
   }
   
   public void insertAccount(int AccountNumber, int Pin, boolean Status,
        double AvailableBalance, double TotalBalance, int Role,
        int TransferAccumulate) {
       
       accounts.add(new Account(AccountNumber, Pin, Status, AvailableBalance, TotalBalance,
            0, 0, true));
   }
   
   public boolean isNumberExist(int userAccountNumber) {
       
       if(getAccount(userAccountNumber) != null) {
           return true;
       } else {
           return false;
       }
   }
   
   public boolean isResetAvailable(int userAccountNumber) {
       return getAccount(userAccountNumber).getResetReq();
   }
   
   public void setResetReq(int userAccountNumber, boolean state) {
       getAccount(userAccountNumber).setResetReq(state);
   }
} 