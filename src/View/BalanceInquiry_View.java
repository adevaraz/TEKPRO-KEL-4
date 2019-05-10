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
public class BalanceInquiry_View {
    Screen screen = new Screen();
    
    public void balanceInq(double availableBalance, double totalBalance) {
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance);
      screen.displayMessage("\n - Total balance: ");
      screen.displayDollarAmount(totalBalance);
      screen.displayMessageLine("");
    }
}
