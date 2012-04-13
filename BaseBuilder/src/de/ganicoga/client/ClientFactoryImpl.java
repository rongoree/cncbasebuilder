package de.ganicoga.client;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import de.ganicoga.client.model.Refs.LevelMode;
import de.ganicoga.client.model.Structure;
import de.ganicoga.client.model.resource.Accumulator;
import de.ganicoga.client.model.resource.Crystal;
import de.ganicoga.client.model.resource.Harvester;
import de.ganicoga.client.model.resource.HarvesterCrystal;
import de.ganicoga.client.model.resource.HarvesterTiberium;
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
import de.ganicoga.client.view.BaseView;
import de.ganicoga.client.view.BaseViewImpl;
import de.ganicoga.client.view.SelectionView;
import de.ganicoga.client.view.SelectionViewImpl;
import de.ganicoga.client.view.StatsView;
import de.ganicoga.client.view.StatsViewImpl;

public class ClientFactoryImpl implements ClientFactory {
	private static final EventBus eb = new SimpleEventBus();
	
	private static final SelectionView sv = new SelectionViewImpl();
	private static final BaseView bv = new BaseViewImpl();
	private static final StatsView stv = new StatsViewImpl();

	private boolean insertMode;

	private LevelMode levelMode = LevelMode.NONE;

	@Override
	public EventBus getEventBus() {
		return eb;
	}

	@Override
	public SelectionView getSelectionView() {
		return sv;
	}

	@Override
	public BaseView getBaseView() {
		return bv;
	}

	@Override
	public Structure getStructure(int id) {
		switch (id) {
			case 1:
				return new Tiberium();
			case 2:
				return new Crystal();
			case 3:
				return new Harvester();
			case 4:
				return new HarvesterTiberium();
			case 5:
				return new HarvesterCrystal();
			case 6:
				return new ConstructionYard();
			case 7:
				return new PowerPlant();
			case 8:
				return new Refinery();
			case 9:
				return new Silo();
			case 10:
				return new Accumulator();
			case 11:
				return new CommandCenter();
			case 12:
				return new Factory();
			case 13:
				return new Barracks();
			case 14:
				return new AirField();
			case 15:
				return new DefenseHQ();
			case 16:
				return new DefenseFacility();
			case 17:
				return new SkySupport();
			case 18:
				return new FalconSupport();
			case 19:
				return new IonSupport();
			}
			return null;
	}

	@Override
	public boolean isInsertMode() {
		return insertMode;
	}
	@Override
	public void setInsertMode(boolean value) {
		insertMode = value;		
	}

	@Override
	public StatsView getStatsView() {
		return stv;
	}

	@Override
	public LevelMode getLevelMode() {
		return levelMode;
	}

	@Override
	public void setLevelMode(LevelMode levelMode) {
		this.levelMode  = levelMode;
		
	}
}
