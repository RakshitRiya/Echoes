package com.example.game3.engine;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.game3.Shared;
import com.example.game3.fragments.DifficultySelectFragment;
import com.example.talkingfingers.R;
import com.example.game3.fragments.GameFragment;

import java.util.ArrayList;
import java.util.List;

public class ScreenController {

	private static ScreenController mInstance = null;
	private static List<Screen> openedScreens = new ArrayList<Screen>();
	private FragmentManager mFragmentManager;
	private ScreenController() {
	}
	public static ScreenController getInstance() {
		if (mInstance == null) {
			mInstance = new ScreenController();
		}
		return mInstance;
	}
	public enum Screen {
		DIFFICULTY,
		GAME
	}
	public static Screen getCurrentScreen() {
		return openedScreens.get(openedScreens.size() - 1);
	}
	public void openScreen(Screen screen) {
		mFragmentManager = Shared.activity.getSupportFragmentManager();
		
		/*if (screen == Screen.GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
		} else if (screen == Screen.DIFFICULTY ) {
			openedScreens.remove(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
		}*/
		Fragment fragment = getFragment(screen);
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
		openedScreens.add(screen);
	}
	private Fragment getFragment(Screen screen) {
		switch (screen) {
		case DIFFICULTY:
			return new DifficultySelectFragment();
		case GAME:
			return new GameFragment();
			default:
			break;
		}
		return null;
	}
}
