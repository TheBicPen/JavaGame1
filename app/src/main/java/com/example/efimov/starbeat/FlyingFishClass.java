package com.example.efimov.starbeat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.efimov.starbeat.engine.IntegerCoords2D;
import com.example.efimov.starbeat.engine.IntegerCoords2DBoundingBox;

public class FlyingFishClass extends View {

    public Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private int fishy;
    private int fishspeed;

    private IntegerCoords2D canvasSize;
    private IntegerCoords2D fishCoords;
    private IntegerCoords2DBoundingBox fishBox;


    private int yellwx , yellowy , yellowspeed = 14;
    private Paint yellowpaint = new Paint();

    private int greenx , greeny , greenspeed = 16;
    private Paint greenpaint = new Paint();


    private int redx , redy , redspeed = 22;
    private Paint redpaint = new Paint();

    private boolean touch = false;

    private int Score, livesRemaining;


    private Bitmap backgroundImage;
    private Paint score = new Paint();
    private Bitmap life[] = new Bitmap[2];


    public FlyingFishClass(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);



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

        fishy = 550;
        Score = 0;
        livesRemaining = 3;

        fishCoords = new IntegerCoords2D(fishx, fishy);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasSize = new IntegerCoords2D(getWidth(), getHeight());



        canvas.drawBitmap(backgroundImage,0,0,null);


        int minFishY = fish[0].getHeight();
        int maxFishY = canvasSize.getY() -fish[0].getHeight() * 3;


        fishBox = new IntegerCoords2DBoundingBox(new IntegerCoords2D(fishx, minFishY), new IntegerCoords2D(fishx, maxFishY));
        fishCoords = fishBox.getNearestInsideBox(new IntegerCoords2D(fishx, fishCoords.getY() + fishspeed));

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

        yellwx = yellwx - yellowspeed;

        if(hitBall(yellwx,yellowy))
        {
            Score = Score +5;
            yellwx = - 100;
        }

        if(yellwx<0)
        {
            yellwx = canvasSize.getX() + 21;
            yellowy = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        canvas.drawCircle(yellwx,yellowy,28,yellowpaint);





//                     Green Ball

        greenx = greenx - greenspeed;

        if(hitBall(greenx,greeny))
        {
            Score = Score +10;
            greenx = - 100;
        }

        if(greenx<0)
        {
            greenx = canvasSize.getX() + 21;
            greeny = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        canvas.drawCircle(greenx,greeny,32,greenpaint);








//                     Red Ball

        redx = redx - redspeed;

        if(hitBall(redx,redy)) {
            redx = -100;
            livesRemaining--;

            if(livesRemaining == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(),GameOver.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Score",Score);
                getContext().startActivity(intent);
            }
        }

        if(redx<0)
        {
            redx = canvasSize.getX() + 21;
            redy = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        canvas.drawCircle(redx,redy,35,redpaint);




//        canvas.drawBitmap(life[0],500,15,null);
//        canvas.drawBitmap(life[0],580,15,null);
//        canvas.drawBitmap(life[0],660,15,null);
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
        if(fishx < x && x <(fishx + fish[0].getWidth()) && fishy < y && y < (fishy + fish[0].getHeight()))

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
