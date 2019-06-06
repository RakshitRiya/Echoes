package com.example.game3.events;

import com.example.game3.events.engine.FlipDownCardsEvent;
import com.example.game3.events.engine.GameWonEvent;
import com.example.game3.events.engine.HidePairCardsEvent;
import com.example.game3.events.ui.BackGameEvent;
import com.example.game3.events.ui.DifficultySelectedEvent;
import com.example.game3.events.ui.FlipCardEvent;
import com.example.game3.events.ui.NextGameEvent;
import com.example.game3.events.ui.ResetBackgroundEvent;
import com.example.game3.events.ui.StartEvent;
import com.example.game3.events.ui.ThemeSelectedEvent;


public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
