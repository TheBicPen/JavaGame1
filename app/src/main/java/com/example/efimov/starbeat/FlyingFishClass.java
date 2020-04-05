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

public class FlyingFishClass extends View {

    public Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private  int fishy;
    private int fishspeed;
    private int canvasWidth, canvasHeight;

    private int yellwx , yellowy , yellowspeed = 14;
    private Paint yellowpaint = new Paint();

    private int greenx , greeny , greenspeed = 16;
    private Paint greenpaint = new Paint();


    private int redx , redy , redspeed = 22;
    private Paint redpaint = new Paint();

    private boolean touch = false;

    private int Score,lifecountoffish;


    private Bitmap backgroundimge;
    private Paint score = new Paint();
    private Bitmap life[] = new Bitmap[2];


    public FlyingFishClass(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroundimge = BitmapFactory.decodeResource(getResources(),R.drawable.background);



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
        lifecountoffish = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(backgroundimge,0,0,null);

        int minfishY = fish[0].getHeight();
        int maxfish = canvasHeight -fish[0].getHeight() * 3;

        fishy = fishy + fishspeed;

        if(fishy<minfishY)
        {
            fishy = minfishY;
        }


        if(fishy>maxfish)
        {
            fishy = maxfish;
        }

        fishspeed = fishspeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fish[1], fishx,fishy,null);
            touch =false;

        }

        else {
            canvas.drawBitmap(fish[0], fishx,fishy,null);
        }


        //                     Yellow Ball

        yellwx = yellwx - yellowspeed;

        if(hitball(yellwx,yellowy))
        {
            Score = Score +5;
            yellwx = - 100;
        }

        if(yellwx<0)
        {
            yellwx = canvasWidth + 21;
            yellowy = (int) Math.floor(Math.random() * (maxfish - minfishY)) + minfishY;
        }

        canvas.drawCircle(yellwx,yellowy,28,yellowpaint);





//                     Green Ball

        greenx = greenx - greenspeed;

        if(hitball(greenx,greeny))
        {
            Score = Score +10;
            greenx = - 100;
        }

        if(greenx<0)
        {
            greenx = canvasWidth + 21;
            greeny = (int) Math.floor(Math.random() * (maxfish - minfishY)) + minfishY;
        }

        canvas.drawCircle(greenx,greeny,32,greenpaint);








//                     Red Ball

        redx = redx - redspeed;

        if(hitball(redx,redy)) {
            redx = -100;
            lifecountoffish--;

            if(lifecountoffish == 0)
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
            redx = canvasWidth + 21;
            redy = (int) Math.floor(Math.random() * (maxfish - minfishY)) + minfishY;
        }

        canvas.drawCircle(redx,redy,35,redpaint);




//               fishLife
        for (int i=0; i<3; i++)
        {
            int x = (int) (500 + life[0].getWidth() * 1.5 *i);
            int y = 30;

//            life availbe
            if(i < lifecountoffish)
            {
                canvas.drawBitmap(life[0] , x,y,null);
            }

//                        When Eat Red Ball

            else {
                canvas.drawBitmap(life[1] , x,y,null);

            }
        }


        canvas.drawText("Score:"+Score ,0,60,score);




//        canvas.drawText("Score:"+Score ,0,60,score);

//        canvas.drawBitmap(life[0],500,15,null);
//        canvas.drawBitmap(life[0],580,15,null);
//        canvas.drawBitmap(life[0],660,15,null);
    }


    public boolean hitball(int x,int y)
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
