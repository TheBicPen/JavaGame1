package com.example.efimov.starbeat.ShmupGame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBox;

public interface DrawableEntity {
    IntegerCoords2D getPosition();
    void setPosition(IntegerCoords2D position);

    IntegerCoords2D getSize();
    void setSize(IntegerCoords2D size);
    Drawable getDrawable();


    // Draw
    void draw(Canvas canvas);
}
