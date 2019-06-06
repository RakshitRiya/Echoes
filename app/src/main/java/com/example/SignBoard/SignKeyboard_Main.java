package com.example.SignBoard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.talkingfingers.R;

public class SignKeyboard_Main extends Activity implements View.OnClickListener {

    Button et,enable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signkeyboard);
        et=(Button)findViewById(R.id.edittext);
        et.setOnClickListener(this);
        enable=(Button)findViewById(R.id.button2);
        enable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==enable.getId()) {
            Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            startActivity(intent);
        }
        else {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showInputMethodPicker();
        }
    }
}