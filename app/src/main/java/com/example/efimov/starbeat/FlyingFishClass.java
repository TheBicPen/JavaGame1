package com.example.efimov.starbeat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.efimov.starbeat.ShmupGame.ColliderBall;
import com.example.efimov.starbeat.ShmupGame.DrawableEntity;
import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBox;

import java.util.ArrayList;

public class FlyingFishClass extends View {

    public Bitmap fish[] = new Bitmap[2];
    private int fishspeed;

    private IntegerCoords2D canvasSize;
    private IntegerCoords2D fishCoords;
    private IntegerCoords2DBox fishBox;

    Resources res = getResources();
    ArrayList<DrawableEntity> drawableEntities = new ArrayList<>();

    private IntegerCoords2D yellowCoords = new IntegerCoords2D(0, 0);
    private IntegerCoords2D yellowVelocity = new IntegerCoords2D(-14, 0);
    private Paint yellowpaint = new Paint();


    private IntegerCoords2D greenCoords = new IntegerCoords2D(0, 0);
    private IntegerCoords2D greenVelocity = new IntegerCoords2D(-16, 0);
    private Paint greenpaint = new Paint();


    private IntegerCoords2D redCoords = new IntegerCoords2D(0, 0);
    private IntegerCoords2D redVelocity = new IntegerCoords2D(-22, 0);
    private Paint redpaint = new Paint();

    private ColliderBall blue = new ColliderBall(15, new IntegerCoords2D(0,0), Color.BLUE);

    private boolean touch = false;

    private int Score, livesRemaining;


    private Bitmap backgroundImage;
    private Paint score = new Paint();
    private Bitmap life[] = new Bitmap[2];



    public FlyingFishClass(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(res,R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(res,R.drawable.fish2);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);


        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);



        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);



        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        Score = 0;
        livesRemaining = 3;

        fishCoords = new IntegerCoords2D(res.getInteger(R.integer.InitialFishX), res.getInteger(R.integer.InitialFishY));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasSize = new IntegerCoords2D(getWidth(), getHeight());

        for (DrawableEntity entity: drawableEntities){
            entity.draw(canvas);
        }

        canvas.drawBitmap(backgroundImage,0,0,null);


        int minFishY = fish[0].getHeight();
        int maxFishY = canvasSize.getY() -fish[0].getHeight() * 3;


        fishBox = new IntegerCoords2DBox(new IntegerCoords2D(fishCoords.getX(), minFishY), new IntegerCoords2D(fishCoords.getX(), maxFishY));
        fishCoords = fishBox.getNearestInsideBox(new IntegerCoords2D(fishCoords.getX(), fishCoords.getY() + fishspeed));

        fishspeed = fishspeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fish[1], fishCoords.getX(), fishCoords.getY(),null);
            touch =false;
        }

        else {
            canvas.drawBitmap(fish[0], fishCoords.getX(), fishCoords.getY(),null);
        }


        //                     Yellow Ball

        yellowCoords.setX(yellowCoords.getX() + yellowVelocity.getX());

        if(hitBall(yellowCoords.getX(),yellowCoords.getY()))
        {
            Score += 5;
            yellowCoords.setX(-100);
        }

        if(yellowCoords.getX()<0)
        {
            yellowCoords.setX(canvasSize.getX() + 21);
            yellowCoords.setY((int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY);
        }

        canvas.drawCircle(yellowCoords.getX(),yellowCoords.getY(),28,yellowpaint);



        // blue collider ball
        if(hitBall(blue.getPosition().getX(), blue.getPosition().getY())){
            Score += 100;
            blue.setPosition(new IntegerCoords2D(-10, 0));
        }
        if(blue.getPosition().getX() < 0){
            int x = (canvasSize.getX() + 21);
            int y = ((int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY);
            blue.setPosition(new IntegerCoords2D(x,y));
        }
        blue.setPosition(new IntegerCoords2D(blue.getPosition().getX() - 30, blue.getPosition().getY()));

        blue.draw(canvas);


//                     Green Ball

        greenCoords.setX(greenCoords.getX() + greenVelocity.getX());

        if(hitBall(greenCoords.getX(),greenCoords.getY()))
        {
            Score += 5;
            greenCoords.setX(-100);
        }

        if(greenCoords.getX()<0)
        {
            greenCoords.setX(canvasSize.getX() + 21);
            greenCoords.setY((int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY);
        }

        canvas.drawCircle(greenCoords.getX(),greenCoords.getY(),32,greenpaint);









//                     Red Ball


        redCoords.setX(redCoords.getX() + redVelocity.getX());

        if(hitBall(redCoords.getX(),redCoords.getY()))
        {
            livesRemaining--;
            redCoords.setX(-100);

            if(livesRemaining == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(),GameOver.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Score",Score);
                getContext().startActivity(intent);
            }
        }

        if(redCoords.getX()<0)
        {
            redCoords.setX(canvasSize.getX() + 21);
            redCoords.setY((int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY);
        }

        canvas.drawCircle(redCoords.getX(),redCoords.getY(),35,redpaint);


        canvas.drawBitmap(life[0],500,15,null);
        canvas.drawBitmap(life[0],580,15,null);
        canvas.drawBitmap(life[0],660,15,null);
    }

    private void drawHealth(Canvas canvas, int healthNum) {
        for (int i=0; i<healthNum; i++)
        {
            int x = (int) (500 + life[0].getWidth() * 1.5 *i);
            int y = 30;

//            life availbe
            if(i < livesRemaining)
            {
                canvas.drawBitmap(life[0] , x,y,null);
            }
//                        When Eat Red Ball
            else {
                canvas.drawBitmap(life[1] , x,y,null);

            }
        }
    }


    public boolean hitBall(int x, int y)
    {
        if(fishCoords.getX() < x && x <(fishCoords.getX() + fish[0].getWidth()) && fishCoords.getY() < y && y < (fishCoords.getY() + fish[0].getHeight()))

        {
            return true;
        }
        return false ;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishspeed = -22;
        }
        return true;
    }
}
