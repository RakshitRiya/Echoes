package com.example.game1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.talkingfingers.R;


public class Game1Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game1);
    }

    // when start button is pressed //
    public void start_game(View view)
    {
        Intent i = new Intent(Game1Main.this, Game1Activity.class);
        startActivity(i);finish();
    }

}
