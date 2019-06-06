package com.example.game1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.talkingfingers.R;

public class Game1End extends AppCompatActivity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game1);
        message = (TextView)findViewById(R.id.message);
        Bundle gameData = getIntent().getExtras();
        message.setText(gameData.getString("game_report"));
    }

    public void goto_main_activity(View view){
        Intent i = new Intent(this, Game1Main.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
