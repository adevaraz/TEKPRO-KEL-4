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
public class Admin_View {
    Screen screen = new Screen();
    
    public void viewDispenser(int availableMoney) {
        screen.displayMessageLine("Dispenser Information:");
        screen.displayMessage(" - Amount of money : "); 
        screen.displayDollarAmount((double)availableMoney);
        int count = availableMoney/20;
        
        System.out.println("\n" + count + " bills remaining..");
    }
}
