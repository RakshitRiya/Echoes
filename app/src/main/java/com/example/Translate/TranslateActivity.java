package com.example.Translate;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ocr_reader.activities.OCRMain;
import com.example.talkingfingers.R;

import java.util.ArrayList;
import java.util.Locale;

public class TranslateActivity extends AppCompatActivity {

    EditText editText;
    ImageButton imageView;
    Button button;
    FloatingActionButton video;
    // TextToSpeech tts;
    VideoView videoView;
    int imageId;
    String s="";
    static int indexCounter;
    String grammar[]={" are "," the "," is "," am "," do "," has "," have "," had "," did "," was "," were "," as ",":",";","'"};
    ImageView scanButton;
    ArrayList<Integer> words;
    private static final int AUDIO_RECORD_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        indexCounter=0;
        videoView = findViewById(R.id.vdVw);
        //Set MediaController  to enable play, pause, forward, etc options.
        //Location of Media File




        editText=findViewById(R.id.editText);
        imageView =findViewById(R.id.imageButton);
        button=findViewById(R.id.button);
        video=findViewById(R.id.video);

        scanButton=findViewById(R.id.scanbutton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TranslateActivity.this,OCRMain.class);
                startActivityForResult(intent,999);
            }
        });
        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);



        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    s=editText.getText().toString();

                    s=s.replace(""+1,"one");
                    s=s.replace(""+2,"two");
                    s=s.replace(""+3,"three");
                    s=s.replace(""+4,"four");
                    s=s.replace(""+5,"five");
                    s=s.replace(""+6,"six");
                    s=s.replace(""+7,"seven");
                    s=s.replace(""+8,"eight");
                    s=s.replace(""+9,"nine");
                    s=s.replace(""+0,"ten");
                    s=s.toLowerCase();

                    words = Split(s);
                    if(words.size()!=0) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(0));
                        indexCounter++;
                        //Starting VideoView By Setting MediaController and URI
                        videoView.setVideoURI(uri);
                        videoView.setVisibility(View.VISIBLE);
                        imageView.setEnabled(false);
                        video.setEnabled(false);
                        scanButton.setEnabled(false);
                        editText.setEnabled(false);
                        videoView.requestFocus();
                        videoView.start();
                    }
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer arg0) {
                            videoView.requestFocus();
                            videoView.start();
                        }
                    });


                }
            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                if(indexCounter<words.size()) {
                    Uri uri22 = Uri.parse("android.resource://" + getPackageName() + "/" + words.get(indexCounter++));
                    videoView.setVideoURI(uri22);
                }
                else {
                    videoView.setVisibility(View.INVISIBLE);
                    indexCounter=0;
                    editText.setText("");
                    imageView.setEnabled(true);
                    scanButton.setEnabled(true);
                    editText.setEnabled(true);
                    video.setEnabled(true);

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



        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());




        findViewById(R.id.imageButton).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        editText.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        editText.setText("");
                        editText.setHint("Listening...");
                        break;
                }
                return false;
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hold to record,release to send",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(TranslateActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(TranslateActivity.this, Manifest.permission.RECORD_AUDIO)) {
                        //Show Information about why you need the permission
                        AlertDialog.Builder builder = new AlertDialog.Builder(TranslateActivity.this);
                        builder.setTitle("Need Audio Permission");
                        builder.setMessage("This app needs audio recording permission for voice input.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ActivityCompat.requestPermissions(TranslateActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_RECORD_PERMISSION_CONSTANT);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                        //Previously Permission Request was cancelled with 'Dont Ask Again',
                        // Redirect to Settings after showing Information about why you need the permission
                        AlertDialog.Builder builder = new AlertDialog.Builder(TranslateActivity.this);
                        builder.setTitle("Need Audio Permission");
                        builder.setMessage("This app needs audio recording permission for voice input.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                sentToSettings = true;
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    } else {
                        //just request the permission
                        ActivityCompat.requestPermissions(TranslateActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_RECORD_PERMISSION_CONSTANT);
                    }


                    SharedPreferences.Editor editor = permissionStatus.edit();
                    editor.putBoolean(Manifest.permission.RECORD_AUDIO,true);
                    editor.commit();




                } else {
                    //You already have the permission, just go ahead.
                    proceedAfterPermission();
                }
            }
        });






        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    editText.setText(matches.get(0));
                editText.setText(matches.get(0));
                //tts.speak("You said "+ matches.get(0), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


      /*  tts= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });*/


       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = editText.getText().toString();
                editText.setText(toSpeak);
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });*/



    }



    private void proceedAfterPermission() {
        //We've got the permission, now we can proceed further
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AUDIO_RECORD_PERMISSION_CONSTANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //The External Storage Write Permission is granted to you... Continue your left job...
                proceedAfterPermission();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(TranslateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //Show Information about why you need the permission
                    AlertDialog.Builder builder = new AlertDialog.Builder(TranslateActivity.this);
                    builder.setTitle("Need Recording Permission");
                    builder.setMessage("This app needs audio recording permission for voice input");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();


                            ActivityCompat.requestPermissions(TranslateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, AUDIO_RECORD_PERMISSION_CONSTANT);


                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else {
                    Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(TranslateActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
        if(requestCode==999&&resultCode==RESULT_OK){
            if(data.getStringExtra("OCRDATA")!=null)
            editText.setText(data.getStringExtra("OCRDATA"));
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(TranslateActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }


   /* public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }*/




    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
    public ArrayList<Integer> Split(String query)
    {
        ArrayList<Integer> words=new ArrayList<>();

        String delimiters = "[.?!',]";
        String[] sentences = (query.split(delimiters));
        for(int k=0;k<sentences.length;k++) {
            imageId = getResourseId(this, sentences[k].replaceAll(" ", ""), "raw", getPackageName());
            if (imageId != 0) {
                words.add(imageId);
            }
            else {
                String p="";
                String[] splittedwords = sentences[k].split("\\W+");
                for(int j=0;j<splittedwords.length;j++)
                {
                    if(splittedwords[j].equalsIgnoreCase(p="Yesterday")){
                        p= "yesterday ";
                        splittedwords[j]="";
                        for(int i=0;i<splittedwords.length;i++)
                            p+=splittedwords[i]+" ";
                        Toast.makeText(this,p,Toast.LENGTH_SHORT).show();

                        sentences[k]=p;
                        break;
                    }
                }
                splittedwords = sentences[k].split("\\W+");

                for(int i=0;i<grammar.length;i++)
                {
                    sentences[k]=sentences[k].replaceAll(grammar[i]," ");
                }

                for (int i = 0; i < splittedwords.length; i++) {
                    Mapping mapping=new Mapping();
                    mapping.putData();
                    if(mapping.getData(splittedwords[i])!=null)
                    {
                        splittedwords[i]=mapping.getData(splittedwords[i]);
                    }

                    imageId = getResourseId(this, splittedwords[i], "raw", getPackageName());
                    if (imageId != 0) {
                        words.add(imageId);
                    } else {
                        for (int j = 0; j < splittedwords[i].length(); j++) {
                            imageId = getResourseId(this, "" + splittedwords[i].charAt(j), "raw", getPackageName());
                            if (imageId != 0) {
                                words.add(imageId);
                            } else {
                                Toast.makeText(getApplicationContext(), " No Sign Found for this ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
        return words;

    }

}
