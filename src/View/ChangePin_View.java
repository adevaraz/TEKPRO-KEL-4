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
public class ChangePin_View {
    Screen screen = new Screen();
    
    public void errorInputPin() {
        screen.displayMessageLine("Please enter different pin!");
    }
    
    public void errorValidationPin() {
        screen.displayMessageLine("The pin is not match!");
    }
    
    public void pinChanged() {
        screen.displayMessageLine("Your new pin has been setted");
    }
    
    public void inputPin() {
        screen.displayMessage("Please enter new pin or 0 to Cancel : ");
    }
    
    public void inputValidate() {
        screen.displayMessage("Please enter new pin one more time : ");
    }
}
