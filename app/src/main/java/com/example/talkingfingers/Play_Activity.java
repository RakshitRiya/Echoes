package com.example.talkingfingers;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Learn.learnandplay;

import co.gofynd.gravityview.GravityView;

public class Play_Activity extends AppCompatActivity implements View.OnClickListener{

    ImageView bg;
   private GravityView gravityView2;
    Typeface wooden;

    TextView gamename;

    Button play;
    Button reward;
    Button shop;
    Button leader;
MediaPlayer game;
    RelativeLayout launcher;

    static final int[] catogaries = new int[] {

            R.drawable.teddy2,
            R.drawable.ps42,
            R.drawable.tv2,
            R.drawable.bike2,
            R.drawable.car2,
            R.drawable.jet2,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_play);



         game = MediaPlayer.create(this, R.raw.bg);
         game.setVolume(1.0f,1.0f);
        game.setLooping(true);

        wooden = Typeface.createFromAsset(getAssets(), "wood.ttf");
//
//        gamename= findViewById(R.id.gamename);
//
//        gamename.setTypeface(wooden);

        play = findViewById(R.id.play);
        reward = findViewById(R.id.reward);
        leader = findViewById(R.id.leader);
        shop = findViewById(R.id.shop);

        launcher = findViewById(R.id.launcherbg);


        play.setOnClickListener(this);
        reward.setOnClickListener(this);
        leader.setOnClickListener(this);
        shop.setOnClickListener(this);

        bg  = findViewById(R.id.bg);
        launcher.setBackgroundResource(R.drawable.twf_background);

        gravityView2 = gravityView2.getInstance(this);

        if (gravityView2.deviceSupported()) {
            gravityView2.setImage(bg, R.drawable.launcherbg).center();
        }
        else {
            launcher.setBackgroundResource(R.drawable.twf_background);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gravityView2.unRegisterListener();
        game.pause();


    }

    public void onBackPressed() {
        super.onBackPressed();
        game.pause();
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        //---returns the number of images---
        public int getCount() {
            return catogaries.length;
        }

        //---returns the ID of an item---
        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);

                imageView.setLayoutParams(new GridView.LayoutParams(600, 600));
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setPaddingRelative(5, 50, 5, 100);



            } else {
                imageView = (ImageView) convertView;
                imageView.setLayoutParams(new GridView.LayoutParams(600, 600));
                imageView.setPadding(5, 50, 5, 100);



            }
            imageView.setImageResource(catogaries[position]);
            return imageView;


        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        game.setLooping(true);
        game.start();
        gravityView2.registerListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:

                Intent i = new Intent(this, GameSelect_Activity.class);
                startActivity(i);
                break;

            case R.id.shop:

                Intent j = new Intent(this, learnandplay.class);
                startActivity(j);
                break;
            /*case R.id.reward:

                rew = new Dialog(Play_Activity.this);
                rew.requestWindowFeature(Window.FEATURE_NO_TITLE);

                View rewView  = getLayoutInflater().inflate(R.layout.reward_dialog, null);
                rewView.setBackgroundColor(Color.TRANSPARENT);

                rew.setCancelable(true);
                rew.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                rew.setContentView(rewView);

                Window window = rew.getWindow();
                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

                rew.show();


                close = (Button) rew.findViewById(R.id.close) ;
                rewardcoin = (TextView) rew.findViewById(R.id.rewardcoin);

                rewardcoin.setText("\u20BF"+REWARD);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rew.dismiss();
                    }
                });
                COMMENTED OUT XD
                break;*/


            /*case R.id.shop:

                shopD = new Dialog(Play_Activity.this);
                shopD.requestWindowFeature(Window.FEATURE_NO_TITLE);

                View shopView  = getLayoutInflater().inflate(R.layout.shop_dialog, null);
                shopView.setBackgroundColor(Color.TRANSPARENT);

                shopD.setCancelable(true);
                shopD.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                shopD.setContentView(shopView);

                Window window1 = shopD.getWindow();
                window1.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);



                GridView gridView = (GridView) shopD.findViewById(R.id.grid_view);

                gridView.setAdapter(new ImageAdapter(this));
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {

                        Toast.makeText(getApplicationContext(),
                                " "+position, Toast.LENGTH_SHORT).show();


                    }
                });


                shopD.show();


                close = (Button) shopD.findViewById(R.id.close) ;

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shopD.dismiss();
                    }
                });
                COMMENTED OUT
                break;*/


            case R.id.leader:

                final Dialog leadersD = new Dialog(Play_Activity.this);
                leadersD.requestWindowFeature(Window.FEATURE_NO_TITLE);


                View leadersView  = getLayoutInflater().inflate(R.layout.leaders_dialog, null);
                leadersView.setBackgroundColor(Color.TRANSPARENT);

                leadersD.setCancelable(true);
                leadersD.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                leadersD.setContentView(leadersView);

                Window window3 = leadersD.getWindow();
                window3.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

                leadersD.show();


                Button close = leadersD.findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leadersD.dismiss();
                    }
                });
                break;
        }
    }

}
