package de.ganicoga.client;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import de.ganicoga.client.model.Refs;
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

	private int activeFaction = Refs.GDI;
	private boolean insertMode;

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
		Structure returnStructure = null;
		switch (id) {
		case 1:
			returnStructure = new Tiberium();
			break;
		case 2:
			returnStructure = new Crystal();
			break;
		case 3:
			returnStructure = new Harvester();
			break;
		case 4:
			returnStructure = new HarvesterTiberium();
			break;
		case 5:
			returnStructure = new HarvesterCrystal();
			break;
		case 6:
			returnStructure = new ConstructionYard();
			break;
		case 7:
			returnStructure = new PowerPlant();
			break;
		case 8:
			returnStructure = new Refinery();
			break;
		case 9:
			returnStructure = new Silo();
			break;
		case 10:
			returnStructure = new Accumulator();
			break;
		case 11:
			returnStructure = new CommandCenter();
			break;
		case 12:
			returnStructure = new Factory();
			break;
		case 13:
			returnStructure = new Barracks();
			break;
		case 14:
			returnStructure = new AirField();
			break;
		case 15:
			returnStructure = new DefenseHQ();
			break;
		case 16:
			returnStructure = new DefenseFacility();
			break;
		case 17:
			returnStructure = new SkySupport();
			break;
		case 18:
			returnStructure = new FalconSupport();
			break;
		case 19:
			returnStructure = new IonSupport();
			break;
		}
		return returnStructure;
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
	public int getActiveFaction() {
		return activeFaction;
	}

	@Override
	public void setActiveFaction(int activeFaction) {
		this.activeFaction = activeFaction;
	}
}
