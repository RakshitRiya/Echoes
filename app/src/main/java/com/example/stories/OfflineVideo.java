package com.example.stories;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.talkingfingers.R;

public class OfflineVideo extends AppCompatActivity {
VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_video);


        videoView=findViewById(R.id.videoview);

        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);


        int imageId=getResources().getIdentifier("story","raw",getPackageName());
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+imageId);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}
