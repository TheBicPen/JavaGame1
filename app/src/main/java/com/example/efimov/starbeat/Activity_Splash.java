package com.example.efimov.starbeat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_Splash extends AppCompatActivity {

    public  static int  SplashScreen = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity__splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Activity_Splash.this,MainActivity.class);
                startActivity(intent);
            }
        },SplashScreen);
    }
}
