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
public class Global_View {
    Screen screen = new Screen();
    
    public void transactionCanceled() {
        screen.displayMessageLine("Canceling transaction...");
    }
    
    public void invalidSelection() {
        screen.displayMessageLine("\nInvalid selection. Try again.");
    }
}
