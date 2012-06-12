package de.ganicoga.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.widget.ExtendedTextBox;
import com.google.gwt.event.dom.client.ChangeEvent;

public class AppViewImpl extends Composite implements AppView {

	private static AppViewImplUiBinder uiBinder = GWT
			.create(AppViewImplUiBinder.class);

	interface AppViewImplUiBinder extends UiBinder<Widget, AppViewImpl> {
	}

	public AppViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		
		factionBox.addItem("GDI");
		factionBox.addItem("NOD");
		//TODO factionBox.addItem("Forgotten");

	}

	@UiField
	HasWidgets selectionContent;
	@UiField
	HasWidgets baseContent;
	@UiField
	HasWidgets statsContent;
	@UiField
	ExtendedTextBox configBox;
	@UiField
	Button selectButton;
	@UiField
	Button loadButton;
	@UiField
	ListBox factionBox;

	private Presenter presenter;

	@Override
	public HasWidgets getSouth() {
		return selectionContent;
	}

	@Override
	public HasWidgets getCenter() {
		return baseContent;
	}

	@Override
	public HasWidgets getEast() {
		return statsContent;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	@UiHandler("loadButton")
	void onLoadButtonClick(ClickEvent event) {
		presenter.onLoadConfigClick(validateConfig(configBox.getText()));
	}

	@UiHandler("selectButton")
	void onSelectButtonClick(ClickEvent event) {
		configBox.setFocus(true);
	}

	@UiHandler("clearButton")
	void onClearButtonClick(ClickEvent event) {
		presenter.onClearClick();
	}

	// Enter Event
	@UiHandler("configBox")
	void onConfigBoxKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			presenter.onLoadConfigClick(validateConfig(configBox.getText()));
		}
	}

	// Select Event
	@UiHandler("configBox")
	void onConfigBoxFocus(FocusEvent event) {
		configBox.selectAll();
	}

	@UiHandler("configBox")
	void onConfigBoxChange(ValueChangeEvent<String> event) {
		if (validateConfig(configBox.getText()).length() > 0)
			loadButton.setEnabled(true);
		else {
			loadButton.setEnabled(false);
		}
	}

	@UiHandler("configBox")
	void onConfigBoxKeyUp(KeyUpEvent event) {
		if (validateConfig(configBox.getText()).length() > 0)
			loadButton.setEnabled(true);
		else {
			loadButton.setEnabled(false);
		}
	}

	@Override
	public void setConfigText(String config) {
		configBox.setText(config);

	}

	private String validateConfig(String s) {
		if (s.startsWith("#")) {
			s = s.substring(1);
		}
		return s;
	}
	@UiHandler("factionBox")
	void onFactionBoxChange(ChangeEvent event) {
		presenter.onFactionChange(factionBox.getSelectedIndex());
	}
}
