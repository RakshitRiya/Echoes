package com.example.game3.themes;

import android.graphics.Bitmap;

import com.example.game3.Shared;
import com.example.game3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Theme {

    public static String URI_DRAWABLE = "drawable://";
    public  int id;
    public String backgroundImageUrl;
    public List<String> tileImageUrls;
    public List<String> tileImageLetters;

    public Theme()
	{
		id = 1;
        backgroundImageUrl = "drawable://" + "back_animals";
        tileImageUrls = new ArrayList<String>();
        tileImageLetters = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 26; i++) {
            tileImageUrls.add(URI_DRAWABLE+ String.format("%s", ((char)(i+96))));
            tileImageLetters.add(URI_DRAWABLE+ String.format("alpha_%s", ((char)(i+96))));
		}
	}
    public static Bitmap getBackgroundImage(Theme theme) {
        String drawableResourceName = theme.backgroundImageUrl.substring(URI_DRAWABLE.length());
        int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
        Bitmap bitmap = Utils.scaleDown(drawableResourceId, Utils.screenWidth(), Utils.screenHeight());
        return bitmap;
    }
}
