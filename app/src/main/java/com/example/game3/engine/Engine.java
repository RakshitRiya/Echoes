package com.example.game3.engine;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.game3.Memory;
import com.example.game3.events.engine.HidePairCardsEvent;
import com.example.game3.events.ui.DifficultySelectedEvent;
import com.example.game3.events.ui.FlipCardEvent;
import com.example.game3.events.ui.ResetBackgroundEvent;
import com.example.game3.utils.Utils;
import com.example.talkingfingers.GameSelect_Activity;
import com.example.talkingfingers.R;
import com.example.game3.Music;
import com.example.game3.Shared;
import com.example.game3.engine.ScreenController.Screen;
import com.example.game3.events.EventObserverAdapter;
import com.example.game3.events.engine.FlipDownCardsEvent;
import com.example.game3.events.engine.GameWonEvent;
import com.example.game3.events.ui.BackGameEvent;
import com.example.game3.events.ui.NextGameEvent;
import com.example.game3.events.ui.StartEvent;
import com.example.game3.events.ui.ThemeSelectedEvent;
import com.example.game3.model.BoardArrangment;
import com.example.game3.model.BoardConfiguration;
import com.example.game3.model.Game;
import com.example.game3.model.GameState;
import com.example.game3.themes.Theme;
import com.example.game3.PopupManager;
import com.example.game3.utils.Clock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.example.game3.Game3Activity.stars;

public class Engine extends EventObserverAdapter {

	private static Engine mInstance = null;
	private Game mPlayingGame = null;
	private int mFlippedId = -1;
	private int mToFlip = -1;
	private ScreenController mScreenController;
	private Theme mSelectedTheme;
	private ImageView mBackgroundImage;
	private Handler mHandler;

	private Engine() {
		mScreenController = ScreenController.getInstance();
		mHandler = new Handler();
	}

	public static Engine getInstance() {
		if (mInstance == null) {
			mInstance = new Engine();
		}
		return mInstance;
	}

	public void start() {
		Shared.eventBus.listen(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.listen(FlipCardEvent.TYPE, this);
		Shared.eventBus.listen(StartEvent.TYPE, this);
		Shared.eventBus.listen(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.listen(BackGameEvent.TYPE, this);
		Shared.eventBus.listen(NextGameEvent.TYPE, this);
		Shared.eventBus.listen(ResetBackgroundEvent.TYPE, this);
	}

	public void stop() {
		mPlayingGame = null;
		mBackgroundImage.setImageDrawable(null);
		mBackgroundImage = null;
		mHandler.removeCallbacksAndMessages(null);
		mHandler = null;

		Shared.eventBus.unlisten(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(FlipCardEvent.TYPE, this);
		Shared.eventBus.unlisten(StartEvent.TYPE, this);
		Shared.eventBus.unlisten(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(BackGameEvent.TYPE, this);
		Shared.eventBus.unlisten(NextGameEvent.TYPE, this);
		Shared.eventBus.unlisten(ResetBackgroundEvent.TYPE, this);

		mInstance = null;
	}
	@Override
	public void onEvent(ResetBackgroundEvent event) {
		Drawable drawable = mBackgroundImage.getDrawable();
		if (drawable != null) {
			((TransitionDrawable) drawable).reverseTransition(2000);
		} else {
			new AsyncTask<Void, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
					return bitmap;
				}

				protected void onPostExecute(Bitmap bitmap) {
					mBackgroundImage.setImageBitmap(bitmap);
				}

			}.execute();
		}
	}

	@Override
	public void onEvent(NextGameEvent event) {
		PopupManager.closePopup();
		int difficulty = mPlayingGame.boardConfiguration.difficulty;
		if (mPlayingGame.gameState.achievedStars == 3 && difficulty < 6) {
			difficulty++;
		}
		Shared.eventBus.notify(new DifficultySelectedEvent(difficulty));
	}

	@Override
	public void onEvent(BackGameEvent event) {
		PopupManager.closePopup();
		mScreenController.openScreen(Screen.DIFFICULTY);
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		mSelectedTheme = new Theme();
		mScreenController.openScreen(Screen.DIFFICULTY);
		AsyncTask<Void, Void, TransitionDrawable> task = new AsyncTask<Void, Void, TransitionDrawable>() {

			@Override
			protected TransitionDrawable doInBackground(Void... params) {
				Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
				Bitmap backgroundImage = Theme.getBackgroundImage(mSelectedTheme);
				backgroundImage = Utils.crop(backgroundImage, Utils.screenHeight(), Utils.screenWidth());
				Drawable backgrounds[] = new Drawable[2];
				backgrounds[0] = new BitmapDrawable(Shared.context.getResources(), bitmap);
				backgrounds[1] = new BitmapDrawable(Shared.context.getResources(), backgroundImage);
				TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
				return crossfader;
			}

			@Override
			protected void onPostExecute(TransitionDrawable result) {
				super.onPostExecute(result);
				mBackgroundImage.setImageDrawable(result);
				result.startTransition(2000);
			}
		};
		task.execute();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		mFlippedId = -1;
		mPlayingGame = new Game();
		mPlayingGame.boardConfiguration = new BoardConfiguration(event.difficulty);
		mToFlip = mPlayingGame.boardConfiguration.numTiles;

		// arrange board
		arrangeBoard();

		// start the screen
		mScreenController.openScreen(Screen.GAME);
	}

	private void arrangeBoard() {
		BoardConfiguration boardConfiguration = mPlayingGame.boardConfiguration;
		BoardArrangment boardArrangment = new BoardArrangment();

		// build pairs
		// result {0,1,2,...n} // n-number of tiles
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < boardConfiguration.numTiles; i++) {
			ids.add(i);
		}
		// shuffle
		// result {4,10,2,39,...}
		Collections.shuffle(ids);

		// place the board
		List<String> tileImageUrls = new Theme().tileImageUrls;
		Collections.shuffle(tileImageUrls);
		boardArrangment.pairs = new HashMap<Integer, Integer>();
		boardArrangment.tileUrls = new HashMap<Integer, String>();
		boardArrangment.tileLetters=new HashMap<Integer, String>();
		int j = 0;
		for (int i = 0; i < ids.size(); i++) {
			if (i + 1 < ids.size()) {
				// {4,10}, {2,39}, ...
				boardArrangment.pairs.put(ids.get(i), ids.get(i + 1));
				// {10,4}, {39,2}, ...
				boardArrangment.pairs.put(ids.get(i + 1), ids.get(i));
				// {4,
				boardArrangment.tileUrls.put(ids.get(i), tileImageUrls.get(j));
				boardArrangment.tileLetters.put(ids.get(i + 1),tileImageUrls.get(j).replace("//","//alpha_"));
				i++;
				j++;
			}
		}

		mPlayingGame.boardArrangment = boardArrangment;
	}

	@Override
	public void onEvent(FlipCardEvent event) {
		 Log.i("my_tag", "Flip: " + event.id);
		int id = event.id;
		if (mFlippedId == -1) {
			mFlippedId = id;
			 Log.i("my_tag", "Flip: mFlippedId: " + event.id);
		} else {
			if (mPlayingGame.boardArrangment.isPair(mFlippedId, id)) {
				// Log.i("my_tag", "Flip: is pair: " + mFlippedId + ", " + id);
				// send event - hide id1, id2
				Shared.eventBus.notify(new HidePairCardsEvent(mFlippedId, id), 1000);
				// play music
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Music.playCorrent();
					}
				}, 1000);
				mToFlip -= 2;
				if (mToFlip == 0) {
					int passedSeconds = (int) (Clock.getInstance().getPassedTime() / 1000);
					Clock.getInstance().pause();
					int totalTime = mPlayingGame.boardConfiguration.time;
					GameState gameState = new GameState();
					mPlayingGame.gameState = gameState;
					// remained seconds
					gameState.remainedSeconds = totalTime - passedSeconds;
					gameState.passedSeconds = passedSeconds;

					// calc stars
					if (passedSeconds <= totalTime / 2) {
						gameState.achievedStars = 3;
						stars[mPlayingGame.boardConfiguration.difficulty-1]=3;
					} else if (passedSeconds <= totalTime - totalTime / 5) {
						gameState.achievedStars = 2;
						if(stars[mPlayingGame.boardConfiguration.difficulty]<3)
							stars[mPlayingGame.boardConfiguration.difficulty-1]=2;

					} else if (passedSeconds < totalTime) {
						gameState.achievedStars = 1;
						if(stars[mPlayingGame.boardConfiguration.difficulty]<2)
							stars[mPlayingGame.boardConfiguration.difficulty-1]=1;
					} else {
						gameState.achievedStars = 0;
					}

					// calc score
					gameState.achievedScore = mPlayingGame.boardConfiguration.difficulty * gameState.remainedSeconds *new Theme().id;

					// save to memory
					Memory.save(new Theme().id, mPlayingGame.boardConfiguration.difficulty, gameState.achievedStars);
					Memory.saveTime(new Theme().id, mPlayingGame.boardConfiguration.difficulty ,gameState.passedSeconds);
                    int totalStars=0;
                    for(int i:stars){
                        totalStars+=i;
                    }
                    if(totalStars>=4)
                    {
                        GameSelect_Activity.isCompleted1=true;
                    }
                    else
                        GameSelect_Activity.isCompleted1=false;

					Shared.eventBus.notify(new GameWonEvent(gameState), 1200);
				}
			} else {
				// Log.i("my_tag", "Flip: all down");
				// send event - flip all down
				Shared.eventBus.notify(new FlipDownCardsEvent(), 1000);
			}
			mFlippedId = -1;
			// Log.i("my_tag", "Flip: mFlippedId: " + mFlippedId);
		}
	}

	public Game getActiveGame() {
		return mPlayingGame;
	}

	public void setBackgroundImageView(ImageView backgroundImage) {
		mBackgroundImage = backgroundImage;
	}
}
