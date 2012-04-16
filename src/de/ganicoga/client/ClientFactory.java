package de.ganicoga.client;

import com.google.web.bindery.event.shared.EventBus;

import de.ganicoga.client.model.Structure;
import de.ganicoga.client.view.BaseView;
import de.ganicoga.client.view.SelectionView;
import de.ganicoga.client.view.StatsView;

public interface ClientFactory {
	EventBus getEventBus();

	SelectionView getSelectionView();

	BaseView getBaseView();

	Structure getStructure(int convert);

	boolean isInsertMode();

	void setInsertMode(boolean value);

	StatsView getStatsView();
}
