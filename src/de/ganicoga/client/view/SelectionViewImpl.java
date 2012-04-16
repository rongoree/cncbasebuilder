package de.ganicoga.client.view;

import java.util.ArrayList;

import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.widget.DroppableHorizontalPanel;
import de.ganicoga.client.widget.Tile;

public class SelectionViewImpl extends Composite implements SelectionView {

	private static SelectionViewImplUiBinder uiBinder = GWT
			.create(SelectionViewImplUiBinder.class);

	interface SelectionViewImplUiBinder extends
			UiBinder<Widget, SelectionViewImpl> {
	}

	@UiField
	DroppableHorizontalPanel content;
	private Presenter presenter;

	public SelectionViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		content.addDropHandler(new DropEventHandler() {

			@Override
			public void onDrop(DropEvent event) {
				final Tile tile = (Tile) event.getDraggableWidget();
				
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					
					@Override
					public void execute() {
						presenter.onDrop(tile);
						
					}
				});
			}
		});
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setContent(ArrayList<Tile> list) {
		content.getOriginalWidget().clear();
		for(Tile t : list){
			content.getOriginalWidget().add(t);
		}
		
	}
}
