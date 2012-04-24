package de.ganicoga.client.widget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

import de.ganicoga.client.model.HasLevel;

public class TilePopup extends PopupPanel {

	private IntegerBox levelBox;
	private HasLevel structure;
	private Tile tile;

	public TilePopup() {
		super(true, false);
		// init widgets
		FlowPanel Content = new FlowPanel();
		Label levelLabel = new Label("Level:");
		levelBox = new IntegerBox();

		// setup widgets
		levelBox.setWidth("50px");
		levelBox.setMaxLength(2);
		levelBox.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					hide();
				}
			}
		});

		// build
		Content.add(levelLabel);
		Content.add(levelBox);
		this.add(Content);
	}

	public void show(Tile tile) {
		if (tile.getStructure() != null
				&& tile.getStructure() instanceof HasLevel) {
			this.tile = tile;
			this.structure = (HasLevel) tile.getStructure();
			this.setPopupPosition(tile.getAbsoluteLeft(), tile.getAbsoluteTop());
			levelBox.setValue(structure.getLevel());

			this.show();
			levelBox.setFocus(true);
			levelBox.selectAll();
		}
	}

	public boolean updateLevel() {
		if (levelBox.getValue() != null
				&& levelBox.getValue() != structure.getLevel()) {

			structure.setLevel(levelBox.getValue());
			tile.setLevel(structure.getLevel());

			return true;
		}
		return false;
	}
}
