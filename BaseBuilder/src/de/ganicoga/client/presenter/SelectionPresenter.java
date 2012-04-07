package de.ganicoga.client.presenter;

import gwtquery.plugins.draggable.client.events.DragStartEvent;
import gwtquery.plugins.draggable.client.events.DragStartEvent.DragStartEventHandler;
import gwtquery.plugins.draggable.client.events.DragStopEvent;
import gwtquery.plugins.draggable.client.events.DragStopEvent.DragStopEventHandler;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;

import de.ganicoga.client.Main;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.events.ResetCellEvent;
import de.ganicoga.client.model.defense.AntiTankBarrier;
import de.ganicoga.client.model.defense.Barbwire;
import de.ganicoga.client.model.defense.Flak;
import de.ganicoga.client.model.defense.Guardian;
import de.ganicoga.client.model.defense.GuardianCannon;
import de.ganicoga.client.model.defense.MgNest;
import de.ganicoga.client.model.defense.MissileSquad;
import de.ganicoga.client.model.defense.OilSlick;
import de.ganicoga.client.model.defense.PitBull;
import de.ganicoga.client.model.defense.Predator;
import de.ganicoga.client.model.defense.SamSite;
import de.ganicoga.client.model.defense.Scrub;
import de.ganicoga.client.model.defense.Swamp;
import de.ganicoga.client.model.defense.Wall;
import de.ganicoga.client.model.defense.WatchTower;
import de.ganicoga.client.model.defense.Woods;
import de.ganicoga.client.model.resource.Accumulator;
import de.ganicoga.client.model.resource.Crystal;
import de.ganicoga.client.model.resource.Harvester;
import de.ganicoga.client.model.resource.PowerPlant;
import de.ganicoga.client.model.resource.Refinery;
import de.ganicoga.client.model.resource.Silo;
import de.ganicoga.client.model.resource.Tiberium;
import de.ganicoga.client.model.unique.AirField;
import de.ganicoga.client.model.unique.Barracks;
import de.ganicoga.client.model.unique.CommandCenter;
import de.ganicoga.client.model.unique.ConstructionYard;
import de.ganicoga.client.model.unique.DefenseFacility;
import de.ganicoga.client.model.unique.DefenseHQ;
import de.ganicoga.client.model.unique.Factory;
import de.ganicoga.client.model.unique.FalconSupport;
import de.ganicoga.client.model.unique.IonSupport;
import de.ganicoga.client.model.unique.SkySupport;
import de.ganicoga.client.view.SelectionView;
import de.ganicoga.client.view.SelectionView.Presenter;
import de.ganicoga.client.widget.Tile;

public class SelectionPresenter implements Presenter {

	private SelectionView view;
	private ArrayList<Tile> list = new ArrayList<Tile>();;

	public SelectionPresenter(SelectionView view) {
		this.view = view;
		view.setPresenter(this);

		Main.getClientFactory()
				.getEventBus()
				.addHandler(ConfigChangeEvent.TYPE,
						new ConfigChangeEvent.Handler() {

							@Override
							public void onConfigChange(ConfigChangeEvent event) {
								checkVisibility();
							}
						});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	@Override
	public void onDrop(Tile tile) {
		tile.getStructure().incrementCount();

		Main.getClientFactory().getEventBus()
				.fireEvent(new ResetCellEvent(tile.getRow(), tile.getColumn()));

		checkVisibility();

	}

	private void checkVisibility() {
		if(list.size() == 0)
			return;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStructure().getCount() == 0) {
				UIObject.setVisible(
						list.get(i).getElement().getParentElement(), false);
			} else {
				UIObject.setVisible(
						list.get(i).getElement().getParentElement(), true);
			}
		}
	}
	
	private void updateView() {
		for (int i = 0; i < list.size(); i++) {

			list.get(i).addDragStartHandler(new DragStartEventHandler() {

				@Override
				public void onDragStart(DragStartEvent event) {
					Main.getClientFactory().setInsertMode(true);

				}
			});
			list.get(i).addDragStopHandler(new DragStopEventHandler() {

				@Override
				public void onDragStop(DragStopEvent event) {
					Main.getClientFactory().setInsertMode(false);
				}
			});
		}
		
		view.setContent(list);
		
	}
	
	public void setDefenseMode() {
		list.clear();
		list.add(new Tile(new Swamp()));
		list.add(new Tile(new OilSlick()));
		list.add(new Tile(new Scrub()));
		list.add(new Tile(new Woods()));
		list.add(new Tile(new Wall()));
		list.add(new Tile(new Barbwire()));
		list.add(new Tile(new AntiTankBarrier()));
		list.add(new Tile(new MgNest()));
		list.add(new Tile(new Predator()));
		list.add(new Tile(new MissileSquad()));
		list.add(new Tile(new PitBull()));
		list.add(new Tile(new Guardian()));
		list.add(new Tile(new WatchTower()));
		list.add(new Tile(new GuardianCannon()));
		list.add(new Tile(new Flak()));
		list.add(new Tile(new SamSite()));
		updateView();
	}
	

	public void setBaseMode() {
		list.clear();
		list.add(new Tile(new Tiberium()));
		list.add(new Tile(new Crystal()));
		list.add(new Tile(new ConstructionYard()));
		list.add(new Tile(new Harvester()));
		list.add(new Tile(new Silo()));
		list.add(new Tile(new Refinery()));
		list.add(new Tile(new PowerPlant()));
		list.add(new Tile(new Accumulator()));
		list.add(new Tile(new CommandCenter()));
		list.add(new Tile(new Barracks()));
		list.add(new Tile(new Factory()));
		list.add(new Tile(new AirField()));
		list.add(new Tile(new DefenseHQ()));
		list.add(new Tile(new DefenseFacility()));
		list.add(new Tile(new SkySupport()));
		list.add(new Tile(new FalconSupport()));
		list.add(new Tile(new IonSupport()));
		updateView();		
	}
}
