package de.ganicoga.client.presenter;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.ganicoga.client.Main;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.events.ConfigLoadEvent;
import de.ganicoga.client.events.FactionChangeEvent;
import de.ganicoga.client.model.Refs.Faction;
import de.ganicoga.client.view.AppView;

public class AppPresenter implements AppView.Presenter {

	private AppView view;

	public AppPresenter(AppView view) {
		this.view = view;

		view.setPresenter(this);

		Main.getClientFactory()
				.getEventBus()
				.addHandler(ConfigChangeEvent.TYPE,
						new ConfigChangeEvent.Handler() {

							@Override
							public void onConfigChange(ConfigChangeEvent event) {
								AppPresenter.this.view.setConfigText(event
										.getModel().getConfig());
								History.newItem(event.getModel().getConfig(),
										false);

							}
						});

		SelectionPresenter sp = new SelectionPresenter(Main.getClientFactory()
				.getSelectionView());
		//sp.setDefenseMode();
		sp.setBaseMode();
		sp.go(view.getSouth());

		StatsPresenter stp = new StatsPresenter(Main.getClientFactory()
				.getStatsView());
		stp.go(view.getEast());

		BasePresenter bp = new BasePresenter(Main.getClientFactory()
				.getBaseView());
		bp.go(view.getCenter());

	}

	@Override
	public void go(HasWidgets container) {
		container.add(view.asWidget());
	}

	@Override
	public void onClearClick() {
		Main.getClientFactory().getEventBus().fireEvent(new ConfigLoadEvent());
	}

	@Override
	public void onLoadConfigClick(String config) {
		Main.getClientFactory().getEventBus()
				.fireEvent(new ConfigLoadEvent(config));

	}

	@Override
	public void onFactionChange(String value) {
		if (value.equals(Faction.GDI.toString())){
			Main.getClientFactory().getEventBus().fireEvent(new FactionChangeEvent(Faction.GDI));
		}
		else if (value.equals(Faction.NOD.toString())){
			Main.getClientFactory().getEventBus().fireEvent(new FactionChangeEvent(Faction.GDI));
		}
		else{
			Main.getClientFactory().getEventBus().fireEvent(new FactionChangeEvent(Faction.Forgotten));
		}
	}
}
