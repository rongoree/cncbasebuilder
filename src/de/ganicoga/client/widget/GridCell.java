package de.ganicoga.client.widget;

import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import com.google.gwt.user.client.ui.SimplePanel;

import de.ganicoga.client.Resources;

public class GridCell extends DroppableWidget<SimplePanel> {

	private int row;
	private int col;

	public GridCell(int row, int col) {
		initWidget(new SimplePanel());

		this.row = row;
		this.col = col;

		setActiveClass(Resources.INSTANCE.style().acceptingTile());
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return col;
	}

	public void setColumn(int col) {
		this.col = col;
	}

	public void setTile(Tile t) {
		getOriginalWidget().setWidget(t);
	}

	public Tile getTile() {
		return (Tile) getOriginalWidget().getWidget();
	}
}
