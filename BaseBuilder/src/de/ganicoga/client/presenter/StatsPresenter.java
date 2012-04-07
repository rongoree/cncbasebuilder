package de.ganicoga.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

import de.ganicoga.client.Main;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.model.BaseModel;
import de.ganicoga.client.model.Refs;
import de.ganicoga.client.view.StatsView;

public class StatsPresenter implements StatsView.Presenter {

	private StatsView view;

	private int tiberium;
	private int crystal;
	private int credits;
	private int power;

	private int buildings;

	protected double speedCredits;

	protected double speedPower;

	public StatsPresenter(StatsView view) {
		this.view = view;
		view.setPresenter(this);
		view.setVersionNumber(Refs.VERSION_NUMBER);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());

		Main.getClientFactory()
				.getEventBus()
				.addHandler(ConfigChangeEvent.TYPE,
						new ConfigChangeEvent.Handler() {

							@Override
							public void onConfigChange(ConfigChangeEvent event) {
								if (event.getModel() instanceof BaseModel) {
									BaseModel model = (BaseModel) event
											.getModel();

									tiberium = model.getConstantTiberium();
									crystal = model.getConstantCrystal();
									credits = model.getConstantCredits();
									power = model.getConstantPower();

									speedPower = model.getSpeedPower();
									speedCredits = model.getSpeedCredits();

									buildings = model.getBuildings();

									view.setConstructionSlots(String
											.valueOf(buildings - 1));

									recalculate(view.getLevel());

								}
							}
						});

	}

	@Override
	public void recalculate(int level) {

		int cTibCalc = 0;
		int cCryCalc = 0;
		int cCreCalc = 0;
		int cPwrCalc = 0;

		int sCreCalc = 0;
		int sPwrCalc = 0;

		if (level <= 12) {
			cTibCalc = Refs.MINERAL_TABLE[level] * tiberium;
			cCryCalc = Refs.MINERAL_TABLE[level] * crystal;
			cCreCalc = Refs.INFINITE_TABLE[level] * credits;
			cPwrCalc = Refs.INFINITE_TABLE[level] * power;
			sCreCalc = (int) (Refs.INFINITE_TABLE[level] * speedCredits);
			sPwrCalc = (int) (Refs.INFINITE_TABLE[level] * speedPower);
		} else {
			cTibCalc = Refs.mineralFn(level) * tiberium;
			cCryCalc = Refs.mineralFn(level) * crystal;
			cCreCalc = Refs.infiniteFn(level) * credits;
			cPwrCalc = Refs.infiniteFn(level) * power;
			sCreCalc = (int) (Refs.infiniteFn(level) * speedCredits);
			sPwrCalc = (int) (Refs.infiniteFn(level) * speedPower);
		}

		view.setConstantTiberium(String.valueOf(cTibCalc));
		view.setConstantCrystal(String.valueOf(cCryCalc));
		view.setConstantCredits(String.valueOf(cCreCalc));
		view.setConstantPower(String.valueOf(cPwrCalc));
		
		view.setSpeedBonusCredits(String.valueOf(sCreCalc));
		view.setSpeedBonusPower(String.valueOf(sPwrCalc));
		
		view.setTotalTiberium(String.valueOf(cTibCalc/*+packageBonus*/));
		view.setTotalCrystal(String.valueOf(cCryCalc/*+packageBonus*/));
		view.setTotalPower(String.valueOf(cPwrCalc+sPwrCalc/*+packageBonus*/));
		view.setTotalCredits(String.valueOf(cCreCalc+sCreCalc/*+packageBonus*/));
	}
}
