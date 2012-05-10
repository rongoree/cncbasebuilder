package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.FactionChangeEvent.Handler;
import de.ganicoga.client.model.Refs.Faction;

public class FactionChangeEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onFactionChange(FactionChangeEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	private Faction faction;
	public FactionChangeEvent(Faction faction) {
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

	public Faction getFaction() {
		return faction;
	}

}
