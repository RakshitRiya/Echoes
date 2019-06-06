package com.example.game3.events;

import com.example.game3.events.engine.FlipDownCardsEvent;
import com.example.game3.events.engine.GameWonEvent;
import com.example.game3.events.engine.HidePairCardsEvent;
import com.example.game3.events.ui.DifficultySelectedEvent;
import com.example.game3.events.ui.FlipCardEvent;
import com.example.game3.events.ui.NextGameEvent;
import com.example.game3.events.ui.ResetBackgroundEvent;
import com.example.game3.events.ui.StartEvent;
import com.example.game3.events.ui.ThemeSelectedEvent;
import com.example.game3.events.ui.BackGameEvent;


public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();		
	}

}
