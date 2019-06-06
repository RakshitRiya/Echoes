package com.example.game1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.Translate.TranslateActivity.getResourseId;

public class Game1Activity extends AppCompatActivity  {
    String[] strings = {"How you","How greet morning","Which animal","Who primeminister"};
    String[] hints={"me fine","Good Morning","Elephant","Modi"};
    String[][] optionArray={{"Cat","I am fine","Whale","Elephant"},{"Monkey","Giraffe","Good Morning","Tiger"},{"Apple","Elephant","Tree","Mango"},{"Narendra Modi","Elephant","Tree","Mango"}};
    int answers[]={1,2,1,0};
    Button replayButton,hintButton;
    TextView hint;
    //    Button ok;
//    EditText input_val;
    VideoView videoView;
    Button options[];

    ArrayList<Integer> words;
    private static int indexCounter;
//    //    TextView hint;
//    int wrong_attempts = 0,successfull_attempts = 0;

    public ArrayList<Integer> yamSplit(String sentence) {
        String[] splittedwords = sentence.split("\\W+");
        words = new ArrayList<>();
        int imageId;
        for (int i = 0; i < splittedwords.length; i++) {
            imageId = getResourseId(this, splittedwords[i], "raw", getPackageName());
            if (imageId != 0) {
                words.add(imageId);
            } else {
                for (int j = 0; j < splittedwords[i].length(); j++) {
                    imageId = getResourseId(this, "" + splittedwords[i].charAt(j), "raw", getPackageName());
                    words.add(imageId);
                }
            }
        }
        return words;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1_activity);
        hint=findViewById(R.id.game1_hint);
        indexCounter = 1;
        options=new Button[4];
        options[0]=findViewById(R.id.game1_b1);
        options[1]=findViewById(R.id.game1_b2);
        options[2]=findViewById(R.id.game1_b3);
        options[3]=findViewById(R.id.game1_b4);
        replayButton=findViewById(R.id.game1_replay_button);
        hintButton=findViewById(R.id.game1_hintButton);
        final int index = (int) (Math.random() * strings.length); //generating random index between 0 to length of input strings array //
                replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setVisibility(View.VISIBLE);
                words = yamSplit(strings[index].toLowerCase());
                indexCounter=1;
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(0));
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
                videoView.setVisibility(View.VISIBLE);
                replayButton.setVisibility(View.INVISIBLE);
                hintButton.setVisibility(View.INVISIBLE);
                ((Activity) Game1Activity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hint.setText("Question");
                    }
                });
                videoView.start();

                for(int i=0;i<4;i++) {
                    options[i].setVisibility(View.INVISIBLE);
                }
            }
        });
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setVisibility(View.VISIBLE);
                indexCounter=1;
                words = yamSplit(hints[index].toLowerCase());
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(0));
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
                videoView.setVisibility(View.VISIBLE);
                hintButton.setVisibility(View.INVISIBLE);
                ((Activity) Game1Activity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hint.setText("Hint");
                    }
                });
                videoView.start();

                replayButton.setVisibility(View.INVISIBLE);
                for(int i=0;i<4;i++) {
                    options[i].setVisibility(View.INVISIBLE);
                }
            }
        });
        for(int i=0;i<4;i++) {
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((Button)v).getText().toString().equals(optionArray[index][answers[index]])){
                        Intent i = new Intent(Game1Activity.this, Game1End.class);
                        i.putExtra("game_report","Congratulations You have \n\t Won");
                        startActivity(i);
                    }
                    else{
                        Intent i = new Intent(Game1Activity.this, Game1End.class);
                        i.putExtra("game_report","Oops you have lost\n\t");
                        startActivity(i);
                    }
                }
            });
        }
        for(int i=0;i<4;i++) {
            options[i].setText(optionArray[index][i]);
        }
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView = (VideoView) findViewById(R.id.videoviewdisplay);

        words = yamSplit(strings[index].toLowerCase());
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(0));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
videoView.start();
videoView.setVisibility(View.VISIBLE);
        hint.setVisibility(View.VISIBLE);
        hint.setText("Question");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer arg0) {
                Log.i("ajaypagalh", "Playing video");
                videoView.requestFocus();
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

               

                if (indexCounter < words.size()) {
                    Uri uri22 = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(indexCounter++));
                    videoView.setVideoURI(uri22);
                }
                else{
                    for(int i=0;i<4;i++) {
                        options[i].setVisibility(View.VISIBLE);
                    }
                    hint.setVisibility(View.INVISIBLE);
                    videoView.setVisibility(View.INVISIBLE);
                    replayButton.setVisibility(View.VISIBLE);
                    hintButton.setVisibility(View.VISIBLE);
                    for(int i=0;i<4;i++) {
                        options[i].setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Error in mediaplayer", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }
}