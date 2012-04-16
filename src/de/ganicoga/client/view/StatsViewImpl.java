package de.ganicoga.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
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
	@UiField
	Button rememberStats;
	@UiField
	Button acceptConfig;
	@UiField
	Button restoreConfig;
	
	private Presenter presenter;

	public StatsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		acceptConfig.setVisible(false);
		restoreConfig.setVisible(false);
		rememberStats.setTitle("Remember current config and show new stats relative to old config");
		acceptConfig.setTitle("Accept changes to structures and restore the absolute value view");
		restoreConfig.setTitle("Restores the configuration before last clicked Remember");
	}
	
	@UiHandler("rememberStats")
	public void onRememberStatsClick(ClickEvent event){
		presenter.onRememberStatsClick();
		acceptConfig.setVisible(true);
		restoreConfig.setVisible(true);
		acceptConfig.setEnabled(true);
		restoreConfig.setEnabled(true);
	}
	@UiHandler("acceptConfig")
	public void onAcceptConfigClick(ClickEvent event){
		presenter.onAcceptConfigClick();
		acceptConfig.setVisible(false);
		restoreConfig.setVisible(false);
		acceptConfig.setEnabled(false);
		restoreConfig.setEnabled(false);
	}
	@UiHandler("restoreConfig")
	public void onRestoreConfigClick(ClickEvent event){
		presenter.onRestoreConfigClick();
		acceptConfig.setVisible(false);
		restoreConfig.setVisible(false);
		acceptConfig.setEnabled(false);
		restoreConfig.setEnabled(false);
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
