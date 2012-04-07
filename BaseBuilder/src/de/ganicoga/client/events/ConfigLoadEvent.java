package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.ConfigLoadEvent.Handler;

public class ConfigLoadEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onConfigLoad(ConfigLoadEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	private String string;
	public ConfigLoadEvent(String config) {
		this.string = config;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onConfigLoad(this);
	}

	public String getConfig() {
		return string;
	}

}
