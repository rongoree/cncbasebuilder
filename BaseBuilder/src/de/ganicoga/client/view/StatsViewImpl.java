package de.ganicoga.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatsViewImpl extends Composite implements StatsView {

	private static StatsViewImplUiBinder uiBinder = GWT
			.create(StatsViewImplUiBinder.class);

	interface StatsViewImplUiBinder extends UiBinder<Widget, StatsViewImpl> {
	}

	@UiField
	Label continuousTiberium;
	@UiField
	Label continuousCrystal;
	@UiField
	Label continuousPower;
	@UiField
	Label continuousCredits;
	@UiField
	Label packageTiberium;
	@UiField
	Label packageCrystal;
	@UiField
	Label packagePower;
	@UiField
	Label packageCredits;
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
	Label versionInformationField;
	
	private Presenter presenter;

	public StatsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setConstructionSlots(String slots) {
		buildingField.setText(slots);
	}

	@Override
	public void setContinuousTiberium(String tiberium) {
		continuousTiberium.setText(tiberium + "/h");
	}

	@Override
	public void setContinuousCrystal(String crystal) {
		continuousCrystal.setText(crystal + "/h");
	}

	@Override
	public void setContinuousCredits(String credits) {
		continuousCredits.setText(credits + "/h");
	}

	@Override
	public void setContinuousPower(String power) {
		continuousPower.setText(power + "/h");
	}
	
	@Override
	public void setPackageTiberium(String tiberium) {
		packageTiberium.setText(tiberium + "/h");
	}

	@Override
	public void setPackageCrystal(String crystal) {
		packageCrystal.setText(crystal + "/h");
	}

	@Override
	public void setPackageCredits(String credits) {
		packageCredits.setText(credits + "/h");
	}

	@Override
	public void setPackagePower(String power) {
		packagePower.setText(power + "/h");
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
