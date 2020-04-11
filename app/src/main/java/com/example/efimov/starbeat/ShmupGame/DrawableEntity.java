package com.example.efimov.starbeat.ShmupGame;

import android.graphics.Bitmap;

import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBoundingBox;

public interface DrawableEntity {
    IntegerCoords2D getPosition();

    Bitmap getBitmap();

}
