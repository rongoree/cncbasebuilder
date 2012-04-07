package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.ClearEvent.Handler;

public class ClearEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onClear(ClearEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();


	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onClear(this);
	}
}
