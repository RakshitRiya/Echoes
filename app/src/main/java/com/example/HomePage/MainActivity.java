package com.example.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Translate.TranslateActivity;
import com.example.stories.Stories;
import com.example.support.Support_Activity;
import com.example.talkingfingers.Play_Activity;
import com.example.talkingfingers.R;
//import com.labawsrh.aws.introscreen.R;

import co.gofynd.gravityview.GravityView;

public class MainActivity extends AppCompatActivity {

//   private GravityView gravityView;
//    ImageView image_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        image_background = findViewById(R.id.background_image);
//
//
//            gravityView = GravityView.getInstance(this);
//
//               if(!gravityView.deviceSupported()) {
//
//                   Toast.makeText(this, "Device not Supported!!", Toast.LENGTH_SHORT);
//               }
//               else
//               {
//                   gravityView.setImage(image_background,R.drawable.homepagebackground2)
//                           .center();
//               }

        ImageButton supportbtn=findViewById(R.id.button_support);
        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Support_Activity.class);
                startActivity(intent);
            }
        });
        ImageButton playbtn=findViewById(R.id.button_play);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Play_Activity.class);
                startActivity(intent);
            }
        });

        ImageButton storybtn=findViewById(R.id.button_stories);
        storybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Stories.class);
                startActivity(intent);

            }
        });
        ImageButton translatebtn=findViewById(R.id.button_translate);
        translatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TranslateActivity.class);
                startActivity(intent);

            }
        });
    }




//    @Override
//    protected void onResume() {
//        super.onResume();
//        gravityView.registerListener();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        gravityView.unRegisterListener();
//    }

}


