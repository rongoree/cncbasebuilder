package de.ganicoga.client.widget;

import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;

import de.ganicoga.client.Resources;

public class DroppableHorizontalPanel extends DroppableWidget<HorizontalPanel> {

	public DroppableHorizontalPanel() {
		initWidget(new HorizontalPanel());
		setDraggableHoverClass(Resources.INSTANCE.style().rejectingTile());
	}
}
