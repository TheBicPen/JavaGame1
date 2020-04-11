package com.example.efimov.starbeat.ShmupGame;

import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBoundingBox;

public interface Hitbox {
    IntegerCoords2DBoundingBox getHitBoxFromLocation(IntegerCoords2D position);

    int getDistanceToHitBox(Hitbox otherHitBox);


}
