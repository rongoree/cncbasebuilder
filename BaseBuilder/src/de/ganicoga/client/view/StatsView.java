package de.ganicoga.client.view;

import com.google.gwt.user.client.ui.Widget;

import de.ganicoga.client.BasicPresenter;

public interface StatsView {
	public interface Presenter extends BasicPresenter{

		void recalculate();
		
	}

	void setPresenter(Presenter Presenter);

	Widget asWidget();

	void setConstructionSlots(String slots);

	void setContinuousTiberium(String tiberium);

	void setContinuousCrystal(String crystal);

	void setContinuousCredits(String credits);

	void setContinuousPower(String power);
	
	void setPackageTiberium(String tiberium);

	void setPackageCrystal(String crystal);

	void setPackageCredits(String credits);

	void setPackagePower(String power);

	void setVersionNumber(String version);

	void setTotalPower(String power);

	void setTotalCredits(String credits);

	void setTotalTiberium(String tiberium);

	void setTotalCrystal(String crystal);
}
