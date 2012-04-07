package de.ganicoga.client.widget;

import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.draggable.client.events.DragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStartEvent.DragStartEventHandler;
import gwtquery.plugins.draggable.client.events.DragStopEvent;
import gwtquery.plugins.draggable.client.events.DragStopEvent.DragStopEventHandler;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

import de.ganicoga.client.Resources;
import de.ganicoga.client.model.Structure;

public class Tile extends DraggableWidget<SimplePanel> implements
		HasClickHandlers {

	private Structure structure;
	private Image image;
	private int row = -1, column = -1;

	private final String maxSize = "80px";
	private HandlerRegistration handlerRegistration;

	public Tile(Structure structure) {
		this.structure = structure;

		initWidget(new SimplePanel());

		useCloneAsHelper();
		setDraggingCursor(Cursor.POINTER);
		setRevert(RevertOption.ON_INVALID_DROP);
		setRevertDuration(150);
		setAppendTo("body");

		setSize(maxSize, maxSize);

		addDragStartHandler(onDragStartHandler);
		addDragStopHandler(onDragStopHandler);

		if (structure != null) {
			updateImage();
			setTitle(structure.toString());
		}
	}

	public Tile(Structure structure, int row, int col) {
		this(structure);
		this.row = row;
		this.column = col;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
		updateImage();
	}

	private void updateImage() {
		image = new Image(structure.getImageResource());
		//image.setSize(maxSize, maxSize);
		getOriginalWidget().setWidget(image);

	}

	public void setImage(ImageResource image) {
		this.image = new Image(image);
		//this.image.setSize(maxSize, maxSize);
		getOriginalWidget().setWidget(this.image);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		this.handlerRegistration = addDomHandler(handler, ClickEvent.getType());
		return handlerRegistration;
	}

	public boolean hasClickHandler() {
		return (handlerRegistration != null);
	}

	public String toString() {
		return getStructure().toString() + " " + getRow() + " " + getColumn();
	}

	private static DragStartEventHandler onDragStartHandler = new DragStartEventHandler() {

		@Override
		public void onDragStart(DragStartEvent event) {
			event.getDraggableWidget().setStyleName(
					Resources.INSTANCE.style().selectedTile());
		}
	};

	private static DragStopEventHandler onDragStopHandler = new DragStopEventHandler() {

		@Override
		public void onDragStop(DragStopEvent event) {
			event.getDraggableWidget().removeStyleName(
					Resources.INSTANCE.style().selectedTile());
		}
	};

}
