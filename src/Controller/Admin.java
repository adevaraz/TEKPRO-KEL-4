package Controller;

import View.*;

import Model.CashDispenser;

/**
 *
 * @author Zara Veda
 */
public class Admin extends Transaction{
    int newUserNumber, newUserPin;
    double AvailableBalance, TotalBalance;
    private int currentDate, currentMonth, currentYear;
    private final static int CANCELED = 0;
    private Admin_View show = new Admin_View();
    
    public Admin(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, int theCurrentDate,
      int theCurrentMonth, int theCurrentYear) {
        
      super(userAccountNumber, atmScreen, atmBankDatabase);
        
      currentDate = theCurrentDate;
      currentMonth = theCurrentMonth;
      currentYear = theCurrentYear;
    }
    
    public void viewDispenser() {
        CashDispenser_Controller cashD = getCashDispenser();
        
        Screen screen = getScreen();
        
        int availableMoney;
        availableMoney = cashD.getAmountofDispenser();
        show.viewDispenser(availableMoney);
    }
    
//--------------------------------------------------------------
    
    public void depositDis() {
        CashDispenser_Controller cashDispenser = getCashDispenser();
        Screen screen = getScreen();
        boolean end = false;
        
        while(!end) {
            int amount = enterAmount();
        
            if(amount == CANCELED) {
                screen.displayMessageLine("Canceling transaction...");
                end = true;
            } else {
                boolean positive = isAmountPositive(amount);
            
                if(positive) {
                   screen.displayMessageLine("Processing...");
                   cashDispenser.addtoDispenser(amount);
                   screen.displayMessageLine("\nSucceed");
                   end = true;
                } else {
                   screen.displayMessageLine("Please enter positive number!");
                }
            }
        }
    }
    
    public boolean isAmountPositive(int amount) {
        return amount > 0;
    }
    
    public int enterAmount() {
        Keypad keypad = new Keypad();
        Screen screen = getScreen();
        
        screen.displayMessage("Please enter amount of money in bills " +
            "or 0 to Cancel : " );
        int amount = keypad.getInput();
                
        return amount;
    }
   
//--------------------------------------------------------------
    
    public void unblockUser() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        boolean end = false;
        
        while(!end) {
            int user2Unblock = theUserNumber();
        
            boolean exist = bankDatabase.validateBlockedUser(user2Unblock);
            if(exist) {
                boolean state = bankDatabase.getStatus(user2Unblock);
                if(state) {
                    screen.displayMessageLine("This account was unblocked before..");
                } else {
                    unblockingUser(user2Unblock);
                }
                
                end = true;
            } else {
                screen.displayMessageLine("Please enter the right number!");
            }
        }
    }
    
    private int theUserNumber () {
        Keypad keypad = new Keypad();
        Screen screen = getScreen();

        screen.displayMessage("Please enter user account number to be unblock : ");
        int theNumber2Unblock = keypad.getInput();
        
        return theNumber2Unblock;
    }
    
    private void unblockingUser(int userNumber) {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        screen.displayMessageLine("Unblocking...");
        bankDatabase.setStatus(userNumber, true);
        screen.displayMessageLine("\nFinish unblocking");
    }
    
//--------------------------------------------------------------
    
    void changeCurrentDate() {
       Keypad key = new Keypad();
       Screen scr = new Screen();
       boolean end = false;
       int newDate, newMonth, newYear;
       RangeValidate validDate = new RangeValidate(1, 31);
       RangeValidate validMonth = new RangeValidate(1, 12);
       
       while(!end) {
           scr.displayMessage("Enter new date : ");
           newDate = key.getInput();
           scr.displayMessage("Enter new month : ");
           newMonth = key.getInput();
           scr.displayMessage("Enter new year : ");
           newYear = key.getInput();
       
           if(validDate.validateRange(newDate) && validMonth.validateRange(newMonth)) {
                currentDate =  newDate;
                currentMonth = newMonth;
                currentYear = newYear;
                end = true;
           } else {
               scr.displayMessageLine("Please enter the right date!\n");
           }
       }
       
       scr.displayMessageLine("New date has been setted");
    }
    
    public int getDate() {
        return currentDate;
    }
    
    public int getMonth() {
        return currentMonth;
    }
    
    public int getYear() {
        return currentYear;
    }
    
//--------------------------------------------------------------
    
    public void addNewAcc() {
        Screen screen = getScreen();
        BankDatabase bankDatabase = getBankDatabase();
        Keypad keypad = new Keypad();
        
        screen.displayMessageLine("Please input every user data : ");
        screen.displayMessage("User account number : ");
        newUserNumber = keypad.getInput();
        
        
        if(!bankDatabase.isNumberExist(newUserNumber)) {
            screen.displayMessage("User pin : ");
            newUserPin = keypad.getInput();
            screen.displayMessage("Available balance : ");
            AvailableBalance = keypad.getInput();
            screen.displayMessage("Total balance : ");
            TotalBalance = keypad.getInput();
            
            bankDatabase.insertAccount(newUserNumber, newUserPin, true,
                AvailableBalance, TotalBalance, newUserPin, newUserNumber);
            
            screen.displayMessageLine("The account has been added");
        } else {
            screen.displayMessageLine("Sorry, adding new account is failed..");
        }
    }
    
    @Override
    public void execute() {
        // show main menu and get user selection
         boolean userExited = false;
         Screen screen = getScreen();
        
         while (!userExited) {
            int adminSelection = displayAdminMenu();
         // decide how to proceed based on user's menu selection
         switch (adminSelection) {
            // user chose to perform one of three transaction types
            case 1: //VIEW DISPENSER
                viewDispenser();
               break; 
            
            case 2: //DEPOSIT DISPENSER
               depositDis();
               break;
                
            case 3: //UNBLOCK USER
               unblockUser();
               break;
            
            case 4: //CHANGE DATE
               changeCurrentDate();
               break;
               
            case 5: //ADD NEW ACCOUNT
               addNewAcc();
               break;
                
            case 6: // user chose to terminate session //EXIT
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
               
            default:
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      }
    }
    
    private int displayAdminMenu() {
       Keypad keypad = new Keypad();
       Screen screen = getScreen();
        
       screen.displayMessageLine("\n---------------------");
       screen.displayMessageLine("Date : " + currentDate +
               " / " + currentMonth + " / " + currentYear);
       screen.displayMessageLine("---------------------");
       screen.displayMessageLine("\n**Log in as Admin**");
       screen.displayMessageLine("\nAdmin menu :");
       screen.displayMessageLine("1 - View dispenser");
       screen.displayMessageLine("2 - Deposit dispenser");
       screen.displayMessageLine("3 - Unblock user");
       screen.displayMessageLine("4 - Change date");
       screen.displayMessageLine("5 - Add more account");
       screen.displayMessageLine("6 - Exit Admin mode\n");
       screen.displayMessage("Enter a choice: ");
       return keypad.getInput();
    }   
}
