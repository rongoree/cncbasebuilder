package de.ganicoga.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

import de.ganicoga.client.Main;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.model.BaseModel;
import de.ganicoga.client.model.Refs;
import de.ganicoga.client.view.StatsView;

public class StatsPresenter implements StatsView.Presenter {

	private StatsView view;
	
	private BaseModel baseModel;

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
									baseModel = (BaseModel) event
											.getModel();
									recalculate();

								}
							}
						});
	}

	@Override
	public void recalculate() {
		
		//baseModel.update();

		int ctiberium = baseModel
				.getContinuousTiberium();
		int ccrystal = baseModel.getContinuousCrystal();
		int ccredits = baseModel.getContinuousCredits();
		int cpower = baseModel.getContinuousPower();

		int ptiberium = baseModel.getPackageTiberium();
		int pcrystal = baseModel.getPackageCrystal();
		int pcredits = baseModel.getPackageCredits();
		int ppower = baseModel.getPackagePower();

		int buildings = baseModel.getBuildings();

		view.setContinuousTiberium(String
				.valueOf(ctiberium));
		view.setContinuousCrystal(String
				.valueOf(ccrystal));
		view.setContinuousCredits(String
				.valueOf(ccredits));
		view.setContinuousPower(String
				.valueOf(cpower));

		view.setPackageTiberium(String
				.valueOf(ptiberium));
		view.setPackageCrystal(String
				.valueOf(pcrystal));
		view.setPackageCredits(String
				.valueOf(pcredits));
		view.setPackagePower(String.valueOf(ppower));

		view.setTotalTiberium(String
				.valueOf(ctiberium + ptiberium));
		view.setTotalCrystal(String
				.valueOf(ccrystal + pcrystal));
		view.setTotalCredits(String
				.valueOf(ccredits + pcredits));
		view.setTotalPower(String.valueOf(cpower
				+ ppower));

		view.setConstructionSlots(String
				.valueOf(buildings - 1));	
	}
}
