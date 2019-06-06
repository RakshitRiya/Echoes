package com.example.Game2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.talkingfingers.R;

import java.util.ArrayList;

import co.gofynd.gravityview.GravityView;

import static com.example.Game2.Game2Main.flag;

public class FruitsCardCategory extends AppCompatActivity {


    // 0 - 20 animals //
    // 21 - 38 fruits //
    String[] strings = {"orange", "apple", "banana"};
    Button ok;
    EditText input_val;
    ImageView game_image_view;
    LinearLayout my_output_layout;
    VideoView videoview;
    GravityView gravityView;
    ImageView image_background;
    ImageView hangmanstand;
    MediaController mediaController;

    int wrong_attempts = 0, successfull_attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals_card_category);


        videoview = (VideoView) findViewById(R.id.videoviewdisplay);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoview);


        //Gravity Background
        image_background = findViewById(R.id.background_image);
        gravityView = GravityView.getInstance(this);


        // components initialisation //
        ok = (Button) findViewById(R.id.input_button);
        input_val = (EditText) findViewById(R.id.input_value);
        game_image_view = (ImageView) findViewById(R.id.game_images);
        my_output_layout = (LinearLayout) findViewById(R.id.output_layout);
        hangmanstand = findViewById(R.id.game_images1);

//        hint = (TextView)findViewById(R.id.hint);
        // components initialisation //


        //cards listener


        ok.setVisibility(View.INVISIBLE);
        input_val.setVisibility(View.INVISIBLE);
        game_image_view.setVisibility(View.INVISIBLE);
        my_output_layout.setVisibility(View.INVISIBLE);
        hangmanstand.setVisibility(View.INVISIBLE);
        hello();

    }

    void hello()
    {
        //index=in;
        final int index = (int) (Math.random() * strings.length); //generating random index between 0 to length of input strings array //
        int videoId = videoId = getResources().getIdentifier("story", "raw", getPackageName());
        ;

        switch (index) {
            case 0:
                videoId = getResources().getIdentifier("orange", "raw", getPackageName());
                break;
            case 1:
                videoId = getResources().getIdentifier("apple", "raw", getPackageName());
                break;
            case 2:
                videoId = getResources().getIdentifier("banana", "raw", getPackageName());
                break;

            default:
                Toast.makeText(this, "switch case No video found ", Toast.LENGTH_SHORT).show();
                break;
//            case 3:
//                videoId = getResources().getIdentifier("elephant", "raw", getPackageName());
//                break;
//            case 4:
//                videoId = getResources().getIdentifier("tiger", "raw", getPackageName());
//                break;
//            case 5:
//                videoId = getResources().getIdentifier("crocodile", "raw", getPackageName());
//                break;
//            case 6:
//                videoId = getResources().getIdentifier("monkey", "raw", getPackageName());
//                break;
          /*  case 7 : videoId = getResources().getIdentifier("story","raw",getPackageName());
                break;
            case 8 :  videoId = getResources().getIdentifier("story","raw",getPackageName());
                break;
            case 9 :  videoId = getResources().getIdentifier("story","raw",getPackageName());
                break;*/
        }

        try {


            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + videoId);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(uri);
          //  Toast.makeText(this, "Before start", Toast.LENGTH_SHORT).show();
            videoview.requestFocus();
            videoview.start();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "inside catch", Toast.LENGTH_SHORT).show();
        }

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                videoview.requestFocus();
                videoview.start();

                videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        //code to disable button here
                        videoview.setVisibility(View.INVISIBLE);

                        //Visibility
                        hangmanstand.setVisibility(View.VISIBLE);
                        ok.setVisibility(View.VISIBLE);
                        input_val.setVisibility(View.VISIBLE);
                        game_image_view.setVisibility(View.VISIBLE);
                        my_output_layout.setVisibility(View.VISIBLE);

                        //Gravity View
                        if (!gravityView.deviceSupported()) {

                            Toast.makeText(FruitsCardCategory.this, "Device not Supported!!", Toast.LENGTH_SHORT);
                        } else {
                            gravityView.setImage(image_background, R.drawable.hangmanbackground1)
                                    .center();
                        }

                        final String selected_string = strings[index]; // extracting the corresponding string with generated random index //
                        final ArrayList<String> used_characters = new ArrayList<>();

//        if(index>=0 && index<=20)
//            hint.setText("Animal");
//        else if(index>=21 && index<=38)
//            hint.setText("Fruit");


                        // for dynamically adding the text views with the length of selected_String //
                        final TextView[] myTextViews = new TextView[selected_string.length()];
                        for (int i = 0; i < selected_string.length(); i++) {
                            // create a new textview
                            final TextView rowTextView = new TextView(FruitsCardCategory.this);
                            String vowel = "aeiou";
                            // set some properties of rowTextView or something
                            if (vowel.indexOf(selected_string.charAt(i)) < 0)
                                rowTextView.setText("*");
                            else {
                                String a = "" + selected_string.charAt(i);
                                rowTextView.setText(a);
                                successfull_attempts++;
                            }
                            rowTextView.setTextColor(0XFFFFFFFF);
                            rowTextView.setTextSize(20);
                            // add the textview to the linearlayout
                            my_output_layout.addView(rowTextView);
                            // save a reference to the textview for later
                            myTextViews[i] = rowTextView;
                        }


                        // for dynamically adding the text views with the length of selected_String //

                        ok.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    String entered = input_val.getText().toString(); // input character entered by the user //
                                    input_val.setText(""); // removing the character from the display //
                                    if (entered.matches("")) // when the user press the OK button without enetering any character //
                                    {
                                        Toast.makeText(FruitsCardCategory.this, "Enter any character and then press OK", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if (used_characters.contains(entered)) {
                                        Toast.makeText(FruitsCardCategory.this, "USED", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    used_characters.add(entered);

                                    int matched = 0;

                                    for (int i = 0; i < selected_string.length(); i++) {
                                        if (String.valueOf(selected_string.charAt(i)).equals(entered)) {
                                            myTextViews[i].setText(entered);
                                            matched = 1;
                                            successfull_attempts += 1;
                                        }
                                    }

                                    if (matched != 1) {
                                        wrong_attempts += 1;
                                        switch (wrong_attempts) {
                                            case 1:
                                                game_image_view.setImageResource(R.drawable.first);
                                                break;
                                            case 2:
                                                game_image_view.setImageResource(R.drawable.second);
                                                break;
                                            case 3:
                                                game_image_view.setImageResource(R.drawable.third);
                                                break;
                                            case 4:
                                                game_image_view.setImageResource(R.drawable.fourth);
                                                break;
                                            case 5:
                                                game_image_view.setImageResource(R.drawable.fifth);
                                                break;
                                            case 6:
                                                game_image_view.setImageResource(R.drawable.sixth);
                                                break;
                                        }
                                    }

                                    if (successfull_attempts == selected_string.length()) {

                                        if(flag<3)
                                        {
                                            flag++;

                                            Intent i = new Intent(FruitsCardCategory.this, FruitsCardCategory.class);
                                            i.putExtra("game_report", "Congratulations You have \n\t Won");
                                            startActivity(i);finish();

                                        }

                                        else
                                        {
                                            Intent i = new Intent(FruitsCardCategory.this, Game2End.class);
                                            i.putExtra("game_report", "Congratulations You have \n\t Won");
                                            startActivity(i);finish();
                                        }

                                    } else if (wrong_attempts == 6) {
                                        Intent i = new Intent(FruitsCardCategory.this, Game2End.class);
                                        i.putExtra("game_report", "Oops you have lost\n\t" + selected_string);
                                        startActivity(i);finish();
                                    }

                                } catch (Exception e) {
                                    // do something //
                                    Toast.makeText(FruitsCardCategory.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                });
            }
        });


        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
        // Video Player


            //VideoPlayer end


            //Video player
            //  MediaPlayer mediaPlayer = new MediaPlayer();

        }



        @Override
        protected void onResume() {
            super.onResume();
            gravityView.registerListener();
        }

        @Override
        protected void onPause() {
            super.onPause();
            gravityView.unRegisterListener();
        }



    }

