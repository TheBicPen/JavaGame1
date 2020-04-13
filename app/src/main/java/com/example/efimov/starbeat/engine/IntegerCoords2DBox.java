package com.example.efimov.starbeat.engine;

import android.support.annotation.NonNull;
import android.util.Log;

public class IntegerCoords2DBox implements Coordinates2DBox<IntegerCoords2D>{

    private IntegerCoords2D innerCorner;
    private IntegerCoords2D outerCorner;

    public IntegerCoords2DBox(IntegerCoords2D corner1, IntegerCoords2D corner2){
        Log.d("box", String.format("box corners: %d %d %d %d", corner1.getX(), corner1.getY(), corner2.getX(), corner2.getY()));
        IntegerCoords2D[] corners = orderBoxCoordinates(corner1, corner2);
        this.innerCorner = corners[0];
        this.outerCorner = corners[1];
    }

    private static IntegerCoords2D[] orderBoxCoordinates(@NonNull IntegerCoords2D boxCorner1, @NonNull IntegerCoords2D boxCorner2){
        int minX = Math.min(boxCorner1.getX(), boxCorner2.getX());
        int maxX = Math.max(boxCorner1.getX(), boxCorner2.getX());
        int minY = Math.min(boxCorner1.getY(), boxCorner2.getY());
        int maxY = Math.max(boxCorner1.getY(), boxCorner2.getY());
        return new IntegerCoords2D[] { new IntegerCoords2D(minX, minY), new IntegerCoords2D(maxX, maxY)};
    }

    @Override
    public boolean insideBox(@NonNull IntegerCoords2D coordinates2D) {

        boolean xValid = this.innerCorner.getX() <= coordinates2D.getX() && coordinates2D.getX() <= this.outerCorner.getX();
        boolean yValid = this.innerCorner.getY() <= coordinates2D.getY() && coordinates2D.getY() <= this.outerCorner.getY();
        return xValid && yValid;
    }

    @Override
    public IntegerCoords2D getNearestInsideBox(IntegerCoords2D coordinates2D) {
        if(insideBox(coordinates2D)) {
            return coordinates2D;
        } else {
            int x = Math.max(Math.min(coordinates2D.getX(), this.outerCorner.getX()), this.innerCorner.getX());
            int y = Math.max(Math.min(coordinates2D.getY(), this.outerCorner.getY()), this.innerCorner.getY());
            return new IntegerCoords2D(x,y);
        }
    }

}
