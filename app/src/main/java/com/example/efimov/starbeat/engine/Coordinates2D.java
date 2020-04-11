package com.example.efimov.starbeat.engine;

interface Coordinates2D {

    boolean insideBox(Coordinates2D boxCorner1, Coordinates2D boxCorner2, Coordinates2D coordinates2D);

    Coordinates2D getNearestInsideBox(Coordinates2D boxCorner1, Coordinates2D boxCorner2, Coordinates2D coordinates2D);


}
