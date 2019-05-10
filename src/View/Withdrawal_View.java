/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Zara Veda
 */
public class Withdrawal_View {
    Screen screen = new Screen();
    
    public void withdrawalMenu() {
        screen.displayMessageLine("\nWithdrawal Menu:");
        screen.displayMessageLine("1 - $20");
        screen.displayMessageLine("2 - $40");
        screen.displayMessageLine("3 - $60");
        screen.displayMessageLine("4 - $100");
        screen.displayMessageLine("5 - $200");
        screen.displayMessageLine("6 - Other");
        screen.displayMessageLine("7 - Cancel transaction");
        screen.displayMessage("Choose a withdrawal amount: ");
    }
    
    public void errorNotEnoughMoney() {
        screen.displayMessageLine("This ATM has not enough money." +
                    "\nTransaction cancelled...");
    }
}
