package de.ganicoga.client.widget;

import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.draggable.client.events.DragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStartEvent.DragStartEventHandler;
import gwtquery.plugins.draggable.client.events.DragStopEvent;
import gwtquery.plugins.draggable.client.events.DragStopEvent.DragStopEventHandler;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import de.ganicoga.client.Main;
import de.ganicoga.client.Resources;
import de.ganicoga.client.model.HasLevel;
import de.ganicoga.client.model.Refs;
import de.ganicoga.client.model.Structure;

public class Tile extends DraggableWidget<FlowPanel> implements
		HasClickHandlers {

	private Structure structure;
	private Image image;
	private Label levelLabel;
	private int row = -1, column = -1;

	private final String maxSize = "80px";
	private HandlerRegistration handlerRegistration;

	public Tile(Structure structure) {
		this.structure = structure;

		// drag stuff
		initWidget(new FlowPanel());

		useCloneAsHelper();
		setDraggingCursor(Cursor.POINTER);
		setRevert(RevertOption.ON_INVALID_DROP);
		setRevertDuration(150);
		setAppendTo("body");
		setDistance(4);
		//

		addDragStartHandler(onDragStartHandler);
		addDragStopHandler(onDragStopHandler);

		if (structure != null) {
			setTitle(structure.toString());

			if (structure instanceof HasLevel) {
				addClickHandler(onClickHandler);
				levelLabel = new Label();
				setLevel(((HasLevel) structure).getLevel());
			}

			setImage(structure.getImageResource());
		}

		setSize(maxSize, maxSize);
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
		// TODO may conflict with level up/down
		this.structure = structure;
		setImage(structure.getImageResource());
	}


	public void setImage(ImageResource image) {
		this.image = new Image(image);
		
		getOriginalWidget().clear();
		getOriginalWidget().add(this.image);
		
		if(structure instanceof HasLevel){
			getOriginalWidget().add(levelLabel);
			levelLabel.getElement().getStyle().setPosition(Position.RELATIVE);
			levelLabel.getElement().getStyle().setLeft(2, Unit.PX);
			levelLabel.getElement().getStyle().setBottom(20, Unit.PX);
		}
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

	private static ClickHandler onClickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Tile tile = (Tile) event.getSource();
			HasLevel structure = (HasLevel) tile.getStructure();

			if (Main.getClientFactory().getLevelMode()
					.equals(Refs.LevelMode.UP)) {
				structure.setLevel(structure.getLevel() + 1);
			} else if (Main.getClientFactory().getLevelMode()
					.equals(Refs.LevelMode.DOWN)) {
				structure.setLevel(structure.getLevel() - 1);
			}
			
			tile.setLevel(structure.getLevel());

		}
	};

	protected void setLevel(int level) {
		levelLabel.setText(String.valueOf(level));
		
	}

}
