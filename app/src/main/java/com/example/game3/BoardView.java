package com.example.game3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.game3.events.ui.FlipCardEvent;
import com.example.game3.model.BoardArrangment;
import com.example.game3.model.BoardConfiguration;
import com.example.game3.model.Game;
import com.example.game3.utils.Utils;
import com.example.talkingfingers.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardView extends LinearLayout {

	private LayoutParams mRowLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	private LayoutParams mTileLayoutParams;
	private int mScreenWidth;
	private int mScreenHeight;
	private BoardConfiguration mBoardConfiguration;
	private BoardArrangment mBoardArrangment;
	private Map<Integer, TileView> mViewReference;
	private List<Integer> flippedUp = new ArrayList<Integer>();
	private boolean mLocked = false;
	private int mSize;
	public BoardView(Context context) {
		this(context, null);
	}

	public BoardView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER);
		int margin = getResources().getDimensionPixelSize(R.dimen.margine_top);
		int padding = getResources().getDimensionPixelSize(R.dimen.board_padding);
		mScreenHeight = getResources().getDisplayMetrics().heightPixels - margin - padding*2;
		mScreenWidth = getResources().getDisplayMetrics().widthPixels - padding*2 - Utils.px(20);
		mViewReference = new HashMap<Integer, TileView>();
		setClipToPadding(false);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	public static BoardView fromXml(Context context, ViewGroup parent) {
		return (BoardView) LayoutInflater.from(context).inflate(R.layout.board_view, parent, false);
	}

	public void setBoard(Game game) {
		mBoardConfiguration = game.boardConfiguration;
		mBoardArrangment = game.boardArrangment;
		// calc prefered tiles in width and height
		int singleMargin = getResources().getDimensionPixelSize(R.dimen.card_margin);
		float density = getResources().getDisplayMetrics().density;
		singleMargin = Math.max((int) (1 * density), (int) (singleMargin - mBoardConfiguration.difficulty * 2 * density));
		int sumMargin = 0;
		for (int row = 0; row < mBoardConfiguration.numRows; row++) {
			sumMargin += singleMargin * 2;
		}
		int tilesHeight = (mScreenHeight - sumMargin) / mBoardConfiguration.numRows;
		int tilesWidth = (mScreenWidth - sumMargin) / mBoardConfiguration.numTilesInRow;
		mSize = Math.min(tilesHeight, tilesWidth);
		mTileLayoutParams = new LayoutParams(mSize, mSize);
		mTileLayoutParams.setMargins(singleMargin, singleMargin, singleMargin, singleMargin);
		// build the ui
		buildBoard();
	}

	/**
	 * Build the board
	 */
	private void buildBoard() {

		for (int row = 0; row < mBoardConfiguration.numRows; row++) {
			// add row
			addBoardRow(row);
		}

		setClipChildren(false);
	}

	private void addBoardRow(int rowNum) {

		LinearLayout linearLayout = new LinearLayout(getContext());
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.setGravity(Gravity.CENTER);
		Log.i("Set",""+linearLayout.getId());
		for (int tile = 0; tile < mBoardConfiguration.numTilesInRow; tile++) {
			addTile(rowNum * mBoardConfiguration.numTilesInRow + tile, linearLayout);
		}

		// add to this view
		addView(linearLayout, mRowLayoutParams);
		linearLayout.setClipChildren(false);
	}

	private void addTile(final int id, ViewGroup parent) {
		final TileView tileView = TileView.fromXml(getContext(), parent);
		tileView.setLayoutParams(mTileLayoutParams);
		parent.addView(tileView);
		parent.setClipChildren(false);
		mViewReference.put(id, tileView);
		Bitmap bitmap=mBoardArrangment.getTileBitmap(id, mSize);
        tileView.setTileImage(bitmap);
//        new AsyncTask<Void, Void, Bitmap>() {
//
//            @Override
//            protected Bitmap doInBackground(Void... params) {
//                return mBoardArrangment.getTileBitmap(id, mSize);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap result) {
//
//                tileView.setTileImage(result);
//            }
//        }.execute();
////        tileView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Map<TileView, Integer> myNewHashMap = new HashMap<>();
//                for(Map.Entry<Integer, TileView> entry : mViewReference.entrySet()){
//                    myNewHashMap.put((TileView)entry.getValue(),entry.getKey());
//                }
//                if (!mLocked && ((TileView)v).isFlippedDown()) {
//                    ((TileView)v).flipUp();
//                    flippedUp.add((int)myNewHashMap.get((TileView)v));
//                    if (flippedUp.size() == 2) {
//                        mLocked = true;
//                    }
//                    Shared.eventBus.notify(new FlipCardEvent((int)myNewHashMap.get((TileView)v)));
//                }
//            }
//        });
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(tileView, "scaleX", 0.8f, 1f);
		scaleXAnimator.setInterpolator(new BounceInterpolator());
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(tileView, "scaleY", 0.8f, 1f);
		scaleYAnimator.setInterpolator(new BounceInterpolator());
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
		animatorSet.setDuration(500);
		tileView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

	public void flipDownAll() {
		for (Integer id : flippedUp) {
			mViewReference.get(id).flipDown();
		}
		flippedUp.clear();
		mLocked = false;
	}

	public void revealAll(){
		mLocked=true;
		int showTime=777;
		switch(mBoardConfiguration.difficulty){
			case 1:
				showTime=500;
				break;
			case 2:
				showTime=750;
				break;
			case 3:
				showTime=1000;
				break;
			case 4:
				showTime=1500;
				break;
			case 5:showTime=1750;
			break;
			case 6:showTime=2000;
			break;
		}
		FrameLayout fl =(FrameLayout)getParent();
		BoardView bv=(BoardView)fl.getChildAt(0);
		final int linearCount = bv.getChildCount();
		int childCount;
		LinearLayout ll[]=new LinearLayout[linearCount];
		for(int j=0;j<linearCount;j++)
			ll[j] = (LinearLayout) bv.getChildAt(j);
			childCount = ll[0].getChildCount();
			for ( int j = 0; j < linearCount; j++)
				for (int i = 0; i < childCount; i++) {
					TileView v = (TileView) ll[j].getChildAt(i);
					v.flipUp();
				}
		mLocked=true;
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				FrameLayout fl = (FrameLayout) getParent();
				BoardView bv = (BoardView) fl.getChildAt(0);
				final int linearCount = bv.getChildCount();
				int childCount;
				LinearLayout ll[] = new LinearLayout[linearCount];
				for (int j = 0; j < linearCount; j++)
					ll[j] = (LinearLayout) bv.getChildAt(j);
				childCount = ll[0].getChildCount();
				for (int j = 0; j < linearCount; j++)
					for (int i = 0; i < childCount; i++) {
						TileView v = (TileView) ll[j].getChildAt(i);
						v.flipDown();
					}

				flipDownAll();
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						FrameLayout fl = (FrameLayout) getParent();
						BoardView bv = (BoardView) fl.getChildAt(0);
						final int linearCount = bv.getChildCount();
						int childCount;
						LinearLayout ll[] = new LinearLayout[linearCount];
						for (int j = 0; j < linearCount; j++)
							ll[j] = (LinearLayout) bv.getChildAt(j);
						childCount = ll[0].getChildCount();
						for (int j = 0; j < linearCount; j++)
							for (int i = 0; i < childCount; i++) {
								TileView v = (TileView) ll[j].getChildAt(i);
								v.setOnClickListener(new OnClickListener() {

									@Override//CHECK GERE
                                    public void onClick(View v) {
                Map<TileView, Integer> myNewHashMap = new HashMap<>();
                for(Map.Entry<Integer, TileView> entry : mViewReference.entrySet()){
                    myNewHashMap.put(entry.getValue(),entry.getKey());
                }
                if (!mLocked && ((TileView)v).isFlippedDown()) {
                    ((TileView)v).flipUp();
                    flippedUp.add(myNewHashMap.get(v));
                    if (flippedUp.size() == 2) {
                        mLocked = true;
                    }
                    Shared.eventBus.notify(new FlipCardEvent(myNewHashMap.get(v)));
                }
            }
								});
							}
					}
				}, 250);
			}},showTime);
			}
	public void hideCards(int id1, int id2) {
		Log.i("HELLO",""+id1+"\t"+mViewReference.get(id1));
		animateHide(mViewReference.get(id1));
		animateHide(mViewReference.get(id2));
		flippedUp.clear();
		mLocked = false;
	}

	protected void animateHide(final TileView v) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 0f);
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				v.setLayerType(View.LAYER_TYPE_NONE, null);
				v.setVisibility(View.INVISIBLE);
			}
		});
		animator.setDuration(100);
		v.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animator.start();
	}

}
