package de.ganicoga.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import de.ganicoga.client.events.ResetCellEvent.Handler;

public class ResetCellEvent extends GwtEvent<Handler> {
	
	public interface Handler extends EventHandler {
		void onResetCell(ResetCellEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	private int row;
	private int col;
	public ResetCellEvent(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onResetCell(this);
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}

}
