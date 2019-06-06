package com.example.Learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.talkingfingers.R;
import com.mukesh.DrawingView;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

public class learnandplay extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private Button saveButton, penButton, eraserButton, penColorButton, backgroundColorButton,
            loadButton, clearButton;
    private DrawingView drawingView;
    private SeekBar penSizeSeekBar, eraserSizeSeekBar;
    ImageView forward,backward,Eraser,ColorPallete;
    int flag=0;
    int image[];
    RelativeLayout relativelayout;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_learn);
        initializeUI();
        setListeners();

        forward=findViewById(R.id.forward);
        backward=findViewById(R.id.backward);
        relativelayout=findViewById(R.id.fullscreen);
        Eraser = findViewById(R.id.eraser);
        ColorPallete = findViewById(R.id.colorpallete);
        image = new int[]{R.drawable.adraw,R.drawable.bdraw,R.drawable.cdraw,R.drawable.ddraw,R.drawable.edraw,R.drawable.fdraw,R.drawable.drawword1,R.drawable.drawword2,R.drawable.drawword3};
        relativelayout.setBackgroundResource(image[flag]);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag>0){
                    drawingView.clear();
                    flag--;
                    relativelayout.setBackgroundResource(image[flag]);

                }

            }
        });

       backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag<=7)
                {
                    drawingView.clear();
                    flag++;
                    relativelayout.setBackgroundResource(image[flag]);

                }

            }
        });

       Eraser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               drawingView.clear();
           }
       });

        ColorPallete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker = new ColorPicker(learnandplay.this, 100, 100, 100);

            }
        });



    }

    private void setListeners() {
//        saveButton.setOnClickListener(this);
//        penButton.setOnClickListener(this);
//        eraserButton.setOnClickListener(this);
//        penColorButton.setOnClickListener(this);
//        backgroundColorButton.setOnClickListener(this);
//        penSizeSeekBar.setOnSeekBarChangeListener(this);
//        eraserSizeSeekBar.setOnSeekBarChangeListener(this);
//        loadButton.setOnClickListener(this);
//        clearButton.setOnClickListener(this);
    }

    private void initializeUI() {
       drawingView = findViewById(R.id.scratch_pad);
//        saveButton = findViewById(R.id.save_button);
//        loadButton = findViewById(R.id.load_button);
//        penButton = findViewById(R.id.pen_button);
//        eraserButton = findViewById(R.id.eraser_button);
//        penColorButton = findViewById(R.id.pen_color_button);
//        backgroundColorButton = findViewById(R.id.background_color_button);
//        penSizeSeekBar = findViewById(R.id.pen_size_seekbar);
//        eraserSizeSeekBar = findViewById(R.id.eraser_size_seekbar);
//        clearButton = findViewById(R.id.clear_button);
    }

    @Override public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.save_button:
//                drawingView.saveImage(Environment.getExternalStorageDirectory().toString(), "test",
//                        Bitmap.CompressFormat.PNG, 100);
//                break;
//            case R.id.load_button:
//                drawingView.loadImage(BitmapFactory.decodeResource(getResources(), R.raw.image));
//                Log.d("saveImage", "quality cannot better that 100");
//                break;
//            case R.id.pen_button:
//                drawingView.initializePen();
//                break;
//            case R.id.eraser_button:
//                drawingView.initializeEraser();
//                break;
//            case R.id.clear_button:
//                drawingView.clear();
//                break;
//            case R.id.pen_color_button:
//                final ColorPicker colorPicker = new ColorPicker(learnandplay.this, 100, 100, 100);
//                colorPicker.setCallback(
//                        new ColorPickerCallback() {
//                            @Override public void onColorChosen(int color) {
//                                drawingView.setPenColor(color);
//                                colorPicker.dismiss();
//                            }
//                        });
//                colorPicker.show();
//                break;
//            case R.id.background_color_button:
//                final ColorPicker backgroundColorPicker = new ColorPicker(learnandplay.this, 100, 100, 100);
//                backgroundColorPicker.setCallback(
//                        new ColorPickerCallback() {
//                            @Override public void onColorChosen(int color) {
//                                drawingView.setBackgroundColor(color);
//                                backgroundColorPicker.dismiss();
//                            }
//                        });
//                backgroundColorPicker.show();
//                break;
//            default:
//                break;
//        }
    }

    @Override public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int seekBarId = seekBar.getId();
//        if (seekBarId == R.id.pen_size_seekbar) {
//            drawingView.setPenSize(i);
//        } else if (seekBarId == R.id.eraser_size_seekbar) {
//            drawingView.setEraserSize(i);
//        }
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) {
        //Intentionally Empty
    }

    @Override public void onStopTrackingTouch(SeekBar seekBar) {
        //Intentionally Empty
    }
}

