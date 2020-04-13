package com.example.efimov.starbeat.engine;

import android.os.Build;
import android.support.annotation.RequiresApi;

public interface Coordinates2DBox<Vector> {

    public boolean insideBox(Vector coordinates2D);

    public IntegerCoords2D getNearestInsideBox(Vector coordinates2D);
//    public boolean boxOverlaps(Coordinates2DBox box);
}
