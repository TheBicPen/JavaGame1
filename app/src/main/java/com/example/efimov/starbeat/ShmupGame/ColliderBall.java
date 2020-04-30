package com.example.efimov.starbeat.ShmupGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBox;

public class ColliderBall implements DrawableEntity {

    private Paint paint;
    private IntegerCoords2D position;
    private int radius;

    public ColliderBall(int radius, IntegerCoords2D position, int colour) {
        this.radius = radius;
        this.position = position;
        paint = new Paint();
        paint.setColor(colour);
        paint.setAntiAlias(false);
    }

    @Override
    public IntegerCoords2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(IntegerCoords2D position) {
        this.position = position;
    }

    @Override
    public void move(IntegerCoords2D position) {
        this.position = new IntegerCoords2D(this.position.getX() + position.getX(), this.position.getY() + position.getY());
    }


    @Override
    public IntegerCoords2D getSize() {
        return new IntegerCoords2D(2*radius, 2*radius);
    }

    @Override
    public void setSize(IntegerCoords2D size) {
        this.radius = size.getX() / 2;
    }

    @Override
    public Drawable getDrawable() {
        return null;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(position.getX(), position.getY(), radius, paint);
    }
}
