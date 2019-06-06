package com.example.game3.events.ui;

import com.example.game3.events.AbstractEvent;
import com.example.game3.events.EventObserver;
import com.example.game3.themes.Theme;

public class ThemeSelectedEvent extends AbstractEvent {

	public static final String TYPE = ThemeSelectedEvent.class.getName();

    public ThemeSelectedEvent(Theme theme) {
    }

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
