/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BankDatabase;
import View.Keypad;
import View.Screen;

/**
 *
 * @author Zara Veda
 */
public class ChangePin {
    private int newPin;
    public Keypad keypad;
    public int[] theOldPins;
    public BankDatabase bankDatabase;
    
    public ChangePin(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, int[] userOldPins) {
      
      bankDatabase = atmBankDatabase;
      keypad = atmKeypad;
    }
    
    public boolean isNumberSame(int newPin) {
        boolean same = false;
        
        String check = Integer.toString(newPin);
        
        int i = 0;
        
        while(i<4 && !same) {
            int j = i+ 1;
            while(j<4 && !same) {
                if(check.charAt(i) == check.charAt(j)) {
                    same = true;
                }
                j++;
            }
            i++;
        }
        
        return same;
    }
}
