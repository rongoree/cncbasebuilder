package de.ganicoga.client.view;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.BasicPresenter;

public interface AppView {

	public interface Presenter extends BasicPresenter {

		void onClearClick();

		void onLoadConfigClick(String config);

		void onFactionChange(String value);

	}
	
	Widget asWidget();
	
	void setPresenter(Presenter presenter);

	HasWidgets getSouth();

	HasWidgets getCenter();

	HasWidgets getEast();

	void setConfigText(String config);

}
