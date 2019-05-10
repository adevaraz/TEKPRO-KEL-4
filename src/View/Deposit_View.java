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
public class Deposit_View {
    Screen screen = new Screen();
    
    public void inputAmount() {
        screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
    }
    
    public void transactionFailed() {
        screen.displayMessageLine("Your transaction failed.");
    }
    
    public void envelopeReceived() {
        screen.displayMessageLine("\nYour envelope has been received.");
        screen.displayMessageLine("NOTE: The money just deposited will"
            + " not be available until we verify the amount of any "
            + "enclosed cash and your checks clear.");
    }
    
    public void envelopeDeposit() {
        screen.displayMessage("Please insert a deposit envelope containing ");
        screen.displayDollarAmount(20);
    }
        
        
}
