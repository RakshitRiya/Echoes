package com.example.Game2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.talkingfingers.R;


public class Game2Main extends AppCompatActivity {

    ImageView EmotionsCard,AnimalsCard,FruitsCard;
    static int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2_main);

        EmotionsCard = findViewById(R.id.card_emotionsbtn);
        AnimalsCard = findViewById(R.id.card_animalsbtn);
        FruitsCard = findViewById(R.id.fruits_cardbtn);

        EmotionsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emotions = new Intent(Game2Main.this,EmotionsCardCategory.class);
                startActivity(emotions);
            }
        });
        AnimalsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animals = new Intent(Game2Main.this,AnimalsCardCategory.class);
                startActivity(animals);
            }
        });

        FruitsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fruits = new Intent(Game2Main.this,FruitsCardCategory.class);
                startActivity(fruits);
            }
        });
    }

}
