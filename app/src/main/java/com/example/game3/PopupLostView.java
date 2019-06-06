package com.example.game3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.talkingfingers.R;
import com.example.game3.utils.FontLoader;
import com.example.game3.utils.FontLoader.Font;

public class PopupLostView extends RelativeLayout {


    private TextView mTime;

    public PopupLostView(Context context) {
        this(context, null);
    }

    public PopupLostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_popup_lost_view, this, true);
        ImageView mBackButton = findViewById(R.id.button_back);
        ImageView mNextButton = findViewById(R.id.button_next);
        mTime = findViewById(R.id.text);
        FontLoader.setTypeface(context, new TextView[]{mTime}, Font.GROBOLD);
        setBackgroundResource(R.drawable.level_complete);


    }
}
