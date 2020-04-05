package com.example.efimov.starbeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    Button button;
    TextView textView;
    String Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_game_over);

        textView = (TextView)findViewById(R.id.scoretext);

        Score = getIntent().getExtras().get("Score").toString();

        button = (Button)findViewById(R.id.play);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this,MainActivity.class);
                startActivity(intent);
            }
        });


        textView.setText("You Score is "+ Score);


    }
}
