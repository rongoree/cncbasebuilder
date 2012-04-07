package de.ganicoga.client.view;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.BasicPresenter;

public interface BaseView {

	public interface Presenter extends BasicPresenter {
		
	}
	
	Widget asWidget();
	
	void setPresenter(Presenter preseter);

	FlexTable getTable();
	
}
