package de.ganicoga.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.BasicPresenter;
import de.ganicoga.client.widget.Tile;

public interface SelectionView {
	
	public interface Presenter extends BasicPresenter{

		void onDrop(Tile tile);
		
	}

	Widget asWidget();

	void setPresenter(Presenter presenter);

	void setContent(ArrayList<Tile> list);

}
