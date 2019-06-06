package com.example.game3;


import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.example.game3.engine.Engine;
import com.example.game3.engine.ScreenController;
import com.example.game3.events.EventBus;
import com.example.game3.utils.Utils;
import com.example.talkingfingers.R;


public class Game3Activity extends FragmentActivity {

	private ImageView mBackgroundImage;
	public static int stars[]={0,0,0,0,0,0};
MediaPlayer game;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Shared.context = getApplicationContext();
		Shared.engine = Engine.getInstance();
		Shared.eventBus = EventBus.getInstance();

		setContentView(R.layout.activity_game3);
		mBackgroundImage = findViewById(R.id.background_image);

		Shared.activity = this;
		Shared.engine.start();
		Shared.engine.setBackgroundImageView(mBackgroundImage);

		game = MediaPlayer.create(this, R.raw.bg);
		game.setLooping(true);

		// set background
		setBackgroundImage();

		// set menu
		ScreenController.getInstance().openScreen(ScreenController.Screen.DIFFICULTY);


	}

	@Override
	protected void onResume() {
		super.onResume();

		game.setLooping(true);
		game.start();
	}

	@Override
	protected void onPause() {
		super.onPause();

		game.pause();


	}
	@Override
	protected void onDestroy() {
		Shared.engine.stop();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (PopupManager.isShown())
			PopupManager.closePopup();
		if(ScreenController.getCurrentScreen()== ScreenController.Screen.GAME)
		{
			game.pause();
			ScreenController.getInstance().openScreen(ScreenController.Screen.DIFFICULTY);

		}
		else finish();
	}

	private void setBackgroundImage() {
		Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
		bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
		bitmap = Utils.downscaleBitmap(bitmap, 2);
		mBackgroundImage.setImageBitmap(bitmap);
	}

}
