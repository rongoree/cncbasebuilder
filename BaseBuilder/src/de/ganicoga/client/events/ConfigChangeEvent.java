package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.ConfigChangeEvent.Handler;
import de.ganicoga.client.model.AbstractBaseModel;

public class ConfigChangeEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onConfigChange(ConfigChangeEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	private AbstractBaseModel model;
	public ConfigChangeEvent(AbstractBaseModel model) {
		this.model = model;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onConfigChange(this);
	}

	public AbstractBaseModel getModel() {
		return model;
	}

}
