package de.ganicoga.client.model;

import java.util.List;

import de.ganicoga.client.model.resource.Crystal;
import de.ganicoga.client.model.resource.HarvesterCrystal;
import de.ganicoga.client.model.resource.HarvesterTiberium;
import de.ganicoga.client.model.resource.PowerPlant;
import de.ganicoga.client.model.resource.Refinery;
import de.ganicoga.client.model.resource.Tiberium;

public class BaseModel extends AbstractBaseModel {

	private int constantTiberium, constantCrystal, constantPower, constantCredits;
	private double speedPower, speedCredits;
	private int buildings;

	public BaseModel(String token) {
		super(token);
	}

	public BaseModel() {
		super();
	}

	@Override
	protected void collectAll() {

		constantTiberium = 0;
		constantCrystal = 0;
		constantPower = 0;
		speedPower = 0.0d;
		constantCredits = 0;
		speedCredits = 0.0d;
		buildings = 0;

		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				collect(grid[i][j], getNeighbors(i, j));
			}
		}
	}

	@Override
	protected void collect(Structure s, List<Structure> neighbors) {
		if (s == null || s instanceof IsResource) {
			return;
		}
		buildings++;
		if (s instanceof HarvesterTiberium) {
			constantTiberium++;
		} else if (s instanceof HarvesterCrystal) {
			constantCrystal++;
		} else if (s instanceof PowerPlant) {
			constantPower++;

			double speedBonus = 0;
			for (Structure n : neighbors) {
				if (n instanceof HarvesterCrystal || n instanceof Crystal) {
					speedBonus += Refs.SPEED_BONUS;
				}
			}
			speedPower += (speedBonus > Refs.MAX_SPEED_BONUS ? Refs.MAX_SPEED_BONUS
					: speedBonus);

		} else if (s instanceof Refinery) {
			constantCredits++;

			double speedBonus = 0;
			for (Structure n : neighbors) {
				if (n instanceof HarvesterTiberium || n instanceof Tiberium) {
					speedBonus += Refs.SPEED_BONUS;
				}
			}
			speedCredits += (speedBonus > Refs.MAX_SPEED_BONUS ? Refs.MAX_SPEED_BONUS
					: speedBonus);
		}
	}

	public int getConstantTiberium() {
		return constantTiberium;
	}

	public int getConstantCrystal() {
		return constantCrystal;
	}

	public int getConstantPower() {
		return constantPower;
	}

	public int getConstantCredits() {
		return constantCredits;
	}
	
	public double getSpeedCredits() {
		return speedCredits;
	}
	
	public double getSpeedPower() {
		return speedPower;
	}

	public int getBuildings() {
		return buildings;
	}

}
