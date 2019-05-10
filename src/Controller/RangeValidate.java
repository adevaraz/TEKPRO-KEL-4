package Controller;

/**
 *
 * @author Zara Veda
 */
public class RangeValidate {
    private int min;
    private int max;
    
    public RangeValidate(int theMin, int theMax) {
        min = theMin;
        max = theMax;
    }
    
    public boolean validateRange(int theNum) {
        return (validateAbove(theNum) && validateUnder(theNum));
    } 
    
    public boolean validateAbove(int num) {
        if(num >= min) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validateUnder(int num) {
        if(num <= max) {
            return true;
        } else {
            return false;
        }
    }
}
