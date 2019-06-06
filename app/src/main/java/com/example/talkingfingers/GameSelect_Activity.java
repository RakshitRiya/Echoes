package com.example.talkingfingers;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.Game2.Game2Main;
import com.example.game1.Game1Main;
import com.example.game3.Game3Activity;

public class GameSelect_Activity extends AppCompatActivity implements View.OnClickListener {

    public static boolean isCompleted1 = true;
    public static boolean isCompleted2 = true;
    public static boolean isCompleted3 = true;

    private MediaPlayer click, game;


    Animation myAnim;


    Button back,task1,task2,task3, treasure;


    ImageView star1, star2, star3, avatar1, avatar2, avatar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_select);


        click = MediaPlayer.create(this, R.raw.bubble);
        game = MediaPlayer.create(this, R.raw.bg);
        game.setLooping(true);


        back= findViewById(R.id.back);
        task1= findViewById(R.id.task1);
        task2= findViewById(R.id.task2);
        task3= findViewById(R.id.task3);

        star1 = findViewById(R.id.imageView2);
        star2 = findViewById(R.id.imageView3);
        star3 = findViewById(R.id.imageView4);

        avatar1 = findViewById(R.id.imageView5);
        avatar2 = findViewById(R.id.imageView6);
        avatar3 = findViewById(R.id.imageView7);

        treasure = findViewById(R.id.button9);

        task1.setOnClickListener(this);
        task2.setOnClickListener(this);
        task3.setOnClickListener(this);
        back.setOnClickListener(this);
        treasure.setOnClickListener(this);


        if (isCompleted1 && isCompleted2 && isCompleted3) {

        }


//Implementing BOUNCE
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        myAnim.setDuration(1000);

    }

    @Override
    protected void onResume() {
        super.onResume();

        game.setLooping(true);
        game.start();
        if (isCompleted1) {
            task2.setBackgroundResource(R.drawable.t2);
            star1.setVisibility(View.VISIBLE);

            avatar1.setVisibility(View.INVISIBLE);
            avatar2.setVisibility(View.VISIBLE);
            avatar3.setVisibility(View.INVISIBLE);
        }
        if (isCompleted2) {
            task3.setBackgroundResource(R.drawable.t3);
            star2.setVisibility(View.VISIBLE);

            avatar1.setVisibility(View.INVISIBLE);
            avatar2.setVisibility(View.INVISIBLE);
            avatar3.setVisibility(View.VISIBLE);
        }
        if (isCompleted3) {
            treasure.setBackgroundResource(R.drawable.openchest);
            star3.setVisibility(View.VISIBLE);

            avatar1.setVisibility(View.INVISIBLE);
            avatar2.setVisibility(View.INVISIBLE);
            avatar3.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        game.pause();


    }

    public void onBackPressed() {
        super.onBackPressed();

        game.pause();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.task1) {

            click.start();
            task1.startAnimation(myAnim);

            myAnim.setAnimationListener(new Animation.AnimationListener(){
                public void onAnimationStart(Animation a){}
                public void onAnimationRepeat(Animation a){}
                public void onAnimationEnd(Animation a){

                    Intent i=new Intent(GameSelect_Activity.this, Game3Activity.class);
                    startActivity(i);
                }

            });
//            wait(task1);


        } else if (v.getId() == R.id.task2) {


            if(GameSelect_Activity.isCompleted1) {
                click.start();

                task2.startAnimation(myAnim);

                myAnim.setAnimationListener(new Animation.AnimationListener(){
                    public void onAnimationStart(Animation a){}
                    public void onAnimationRepeat(Animation a){}
                    public void onAnimationEnd(Animation a){

                      Intent  i = new Intent(GameSelect_Activity.this, Game2Main.class);
                        startActivity(i);
                    }

                });
            }
        } else if (v.getId() == R.id.task3) {



            if(GameSelect_Activity.isCompleted2) {
                click.start();
                task3.startAnimation(myAnim);

                myAnim.setAnimationListener(new Animation.AnimationListener(){
                    public void onAnimationStart(Animation a){}
                    public void onAnimationRepeat(Animation a){}
                    public void onAnimationEnd(Animation a){

                       Intent i = new Intent(GameSelect_Activity.this, Game1Main.class);
                        startActivity(i);
                    }

                });
            }
        }

        else if(v.getId()== R.id.button9) {

            if (isCompleted1 && isCompleted2 && isCompleted3) {
                treasure.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener(){
                    public void onAnimationStart(Animation a){}
                    public void onAnimationRepeat(Animation a){}
                    public void onAnimationEnd(Animation a){

                       // Intent i = new Intent(GameSelect_Activity.this, WheelActivity.class);
                       // startActivity(i);
                    }

                });
            }
        }

        else if(v.getId()==R.id.back){

            back.startAnimation(myAnim);
            click.start();

            myAnim.setAnimationListener(new Animation.AnimationListener(){
                public void onAnimationStart(Animation a){}
                public void onAnimationRepeat(Animation a){}
                public void onAnimationEnd(Animation a){

                    finish();
                }

            });

        }
    }
}
