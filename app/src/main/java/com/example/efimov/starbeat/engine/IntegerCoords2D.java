package com.example.efimov.starbeat.engine;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;

public class IntegerCoords2D {

    private int x;
    private int y;

    public IntegerCoords2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean insideBox(@NonNull IntegerCoords2D boxCorner1, @NonNull IntegerCoords2D boxCorner2, @NonNull IntegerCoords2D coordinates2D) {
        boolean xValid = (boxCorner1.x <= coordinates2D.x && coordinates2D.x <= boxCorner2.x) || (boxCorner1.x >= coordinates2D.x && coordinates2D.x >= boxCorner2.x);
        boolean yValid = (boxCorner1.y <= coordinates2D.y && coordinates2D.y <= boxCorner2.y) || (boxCorner1.y >= coordinates2D.y && coordinates2D.y >= boxCorner2.y);
        return xValid && yValid;
    }

}
