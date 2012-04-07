package de.ganicoga.client.view;

import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.BasicPresenter;

public interface StatsView {
	public interface Presenter extends BasicPresenter{

		void recalculate(int value);
		
	}

	void setPresenter(Presenter Presenter);

	Widget asWidget();

	int getLevel();

	void setConstructionSlots(String slots);

	void setConstantTiberium(String tiberium);

	void setConstantCrystal(String crystal);

	void setConstantCredits(String credits);

	void setConstantPower(String power);
	
	void setSpeedBonusPower(String power);
	
	void setSpeedBonusCredits(String credits);

	void setVersionNumber(String version);

	void setTotalPower(String power);

	void setTotalCredits(String credits);

	void setTotalTiberium(String tiberium);

	void setTotalCrystal(String crystal);
}
