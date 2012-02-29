package com.QMPotentialTest;
import com.androidplot.series.XYSeries;
 
public class SimpleXYSeries implements XYSeries {
    private static final int[] vals = {0, 25, 55, 2, 80, 30, 99, 0, 44, 6};
 
    // f(x) = x
    @Override
    public Number getX(int index) {
        return index;
    }
 
 
    // range begins at 0

    public Number getMinX() {
        return 0;
    }
 
    // range ends at 9

    public Number getMaxX() {
        return 9;
    }
 
    @Override
    public String getTitle() {
        return "Some Numbers";
    }
 
    // range consists of all the values in vals
    @Override
    public int size() {
        return vals.length;
    }
 

    public void onReadBegin() {
 
    }
 

    public void onReadEnd() {
 
    }
 
    // return vals[index]
    @Override
    public Number getY(int index) {
        // make sure index isnt something unexpected:
        if(index < 0 || index > 9) {
            throw new IllegalArgumentException("Only values between 0 and 9 are allowed.");
        }
        return vals[index];
    }
 
    // smallest value in vals is 0
    public Number getMinY() {
        return 0;
    }
 
    // largest value in vals is 99
    public Number getMaxY() {
        return 99;
    }
}