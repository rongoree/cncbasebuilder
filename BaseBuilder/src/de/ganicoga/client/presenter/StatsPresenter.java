package de.ganicoga.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

import de.ganicoga.client.Main;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.events.ConfigLoadEvent;
import de.ganicoga.client.model.BaseModel;
import de.ganicoga.client.model.Refs;
import de.ganicoga.client.view.StatsView;

public class StatsPresenter implements StatsView.Presenter {

	private StatsView view;

	private BaseModel baseModel;
	private BaseModel rememberedModel;

	private boolean relativeStatsMode = false;;

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
									baseModel = (BaseModel) event.getModel();
									recalculate();

								}
							}
						});
	}

	@Override
	public void recalculate() {

		int ctiberium, ccrystal, ccredits, cpower, ptiberium, pcrystal, pcredits, ppower, buildings, constructionSlots;

		if (relativeStatsMode) {

			ctiberium = baseModel.getContinuousTiberium() - rememberedModel.getContinuousTiberium();
			ccrystal = baseModel.getContinuousCrystal() - rememberedModel.getContinuousCrystal();
			ccredits = baseModel.getContinuousCredits() - rememberedModel.getContinuousCredits();
			cpower = baseModel.getContinuousPower() - rememberedModel.getContinuousPower();

			ptiberium = baseModel.getPackageTiberium() - rememberedModel.getPackageTiberium();
			pcrystal = baseModel.getPackageCrystal() - rememberedModel.getPackageCrystal();
			pcredits = baseModel.getPackageCredits() - rememberedModel.getPackageCredits();
			ppower = baseModel.getPackagePower() - rememberedModel.getPackagePower();

			buildings = baseModel.getBuildings() - rememberedModel.getBuildings();
			constructionSlots = baseModel.getConstructionSlots() - rememberedModel.getConstructionSlots();
			
			
			view.setContinuousTiberium(toSignString(ctiberium));
			view.setContinuousCrystal(toSignString(ccrystal));
			view.setContinuousCredits(toSignString(ccredits));
			view.setContinuousPower(toSignString(cpower));

			view.setPackageTiberium(toSignString(ptiberium));
			view.setPackageCrystal(toSignString(pcrystal));
			view.setPackageCredits(toSignString(pcredits));
			view.setPackagePower(toSignString(ppower));

			view.setTotalTiberium(toSignString(ctiberium + ptiberium));
			view.setTotalCrystal(toSignString(ccrystal + pcrystal));
			view.setTotalCredits(toSignString(ccredits + pcredits));
			view.setTotalPower(toSignString(cpower + ppower));

			view.setConstructionSlots(toSignString(buildings) + "/"
					+ toSignString(constructionSlots));
			

		} else {

			ctiberium = baseModel.getContinuousTiberium();
			ccrystal = baseModel.getContinuousCrystal();
			ccredits = baseModel.getContinuousCredits();
			cpower = baseModel.getContinuousPower();

			ptiberium = baseModel.getPackageTiberium();
			pcrystal = baseModel.getPackageCrystal();
			pcredits = baseModel.getPackageCredits();
			ppower = baseModel.getPackagePower();

			buildings = baseModel.getBuildings();
			constructionSlots = baseModel.getConstructionSlots();
			
			view.setContinuousTiberium(String.valueOf(ctiberium));
			view.setContinuousCrystal(String.valueOf(ccrystal));
			view.setContinuousCredits(String.valueOf(ccredits));
			view.setContinuousPower(String.valueOf(cpower));

			view.setPackageTiberium(String.valueOf(ptiberium));
			view.setPackageCrystal(String.valueOf(pcrystal));
			view.setPackageCredits(String.valueOf(pcredits));
			view.setPackagePower(String.valueOf(ppower));

			view.setTotalTiberium(String.valueOf(ctiberium + ptiberium));
			view.setTotalCrystal(String.valueOf(ccrystal + pcrystal));
			view.setTotalCredits(String.valueOf(ccredits + pcredits));
			view.setTotalPower(String.valueOf(cpower + ppower));

			view.setConstructionSlots(String.valueOf(buildings - 1) + "/"
					+ String.valueOf(constructionSlots));

		}
	}
	
	private String toSignString(int num){
		return num >= 0 ? "+"+num : String.valueOf(num);
	}

	@Override
	public void onRememberStatsClick() {
		rememberedModel = new BaseModel(baseModel.getConfig());
		relativeStatsMode = true;
		recalculate();
	}

	@Override
	public void onAcceptConfigClick() {
		rememberedModel = null;
		relativeStatsMode = false;
		recalculate();
	}

	@Override
	public void onRestoreConfigClick() {
		relativeStatsMode = false;
		if(rememberedModel != null){
			Main.getClientFactory().getEventBus().fireEvent(new ConfigLoadEvent(rememberedModel.getConfig()));
		}
		rememberedModel = null;
	}
}
