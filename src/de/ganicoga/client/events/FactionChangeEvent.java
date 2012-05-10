package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.FactionChangeEvent.Handler;

public class FactionChangeEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onFactionChange(FactionChangeEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	private int faction;
	public FactionChangeEvent(int faction) {
		this.faction = faction;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onFactionChange(this);
	}

	public int getFaction() {
		return faction;
	}

}
