package com.example.efimov.starbeat.engine;

import android.support.annotation.NonNull;

public class IntegerCoords2D implements Coordinates2D<Integer>{


    private int x;
    private int y;

    public IntegerCoords2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }


}
