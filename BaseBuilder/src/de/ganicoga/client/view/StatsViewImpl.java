package de.ganicoga.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatsViewImpl extends Composite implements StatsView {

	private static StatsViewImplUiBinder uiBinder = GWT
			.create(StatsViewImplUiBinder.class);

	interface StatsViewImplUiBinder extends UiBinder<Widget, StatsViewImpl> {
	}

	@UiField
	Label constantTiberium;
	@UiField
	Label constantCrystal;
	@UiField
	Label constantPower;
	@UiField
	Label constantCredits;
	@UiField
	Label speedBonusPower;
	@UiField
	Label speedBonusCredits;
	@UiField
	Label totalTiberium;
	@UiField
	Label totalCrystal;
	@UiField
	Label totalPower;
	@UiField
	Label totalCredits;
	@UiField
	Label buildingField;
	@UiField
	IntegerBox integerBox;
	@UiField
	Label versionInformationField;
	
	private Presenter presenter;

	public StatsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		integerBox.setMaxLength(2);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("integerBox")
	void onIntegerBoxKeyUp(KeyUpEvent event) {
		if (integerBox.getValue() != null && integerBox.getValue() != 0) {
			presenter.recalculate(integerBox.getValue());
		}

	}

	@UiHandler("integerBox")
	void onIntegerBoxMouseWheel(MouseWheelEvent event) {
		int value = integerBox.getValue() + (event.getDeltaY() > 0 ? -1 : 1);
		if (value < 1) {
			value = 1;
		}
		if (value > 99) {
			value = 99;
		}
		integerBox.setValue(value);
		presenter.recalculate(value);
	}

	@UiHandler("integerBox")
	void onIntegerBoxBlur(BlurEvent event) {
		if (integerBox.getValue() == null || integerBox.getValue() == 0) {
			integerBox.setValue(1);
			presenter.recalculate(1);
		}
	}

	@Override
	public int getLevel() {
		return integerBox.getValue();
	}

	@Override
	public void setConstructionSlots(String slots) {
		buildingField.setText(slots);
	}

	@Override
	public void setConstantTiberium(String tiberium) {
		constantTiberium.setText(tiberium + "/h");
	}

	@Override
	public void setConstantCrystal(String crystal) {
		constantCrystal.setText(crystal + "/h");
	}

	@Override
	public void setConstantCredits(String credits) {
		constantCredits.setText(credits + "/h");
	}

	@Override
	public void setConstantPower(String power) {
		constantPower.setText(power + "/h");
	}
	
	@Override
	public void setSpeedBonusPower(String power) {
		speedBonusPower.setText(power + "/h");
	}
	
	@Override
	public void setSpeedBonusCredits(String credits) {
		speedBonusCredits.setText(credits + "/h");
	}

	@Override
	public void setVersionNumber(String version) {
		versionInformationField.setText("Version: " + version);

	}

	@Override
	public void setTotalPower(String power) {
		totalPower.setText(power + "/h");
		
	}

	@Override
	public void setTotalCredits(String credits) {
		totalCredits.setText(credits + "/h");
		
	}

	@Override
	public void setTotalTiberium(String tiberium) {
		totalTiberium.setText(tiberium + "/h");
		
	}

	@Override
	public void setTotalCrystal(String crystal) {
		totalCrystal.setText(crystal + "/h");
		
	}
}
