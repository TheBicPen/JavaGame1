package com.example.efimov.starbeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultyLevel extends AppCompatActivity {

    Button btney , btnmed , btnhr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);

        btney = (Button)findViewById(R.id.btneasy);
        btney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        btnmed = (Button)findViewById(R.id.btnmedium);
        btnmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyLevel.this,MainActivity.class);
                startActivity(intent);

            }
        });




        btnhr =(Button)findViewById(R.id.btnhard);
        btnhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
