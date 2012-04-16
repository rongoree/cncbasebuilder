package de.ganicoga.client.model;

import java.util.List;


public class DefenseModel extends AbstractBaseModel {


	private int antiAir, antiInfantry, antiVehicle, defenseStructure;
	private int defensePoints;
	
	public DefenseModel(String token) {
		super(token);
	}

	public DefenseModel() {
		super();
	}
	
	@Override
	protected void collectAll() {
		antiAir = 0;
		antiInfantry = 0;
		antiVehicle = 0;
		defensePoints = 0;
	
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				collect(grid[i][j], getNeighbors(i, j));
			}
		}
	}

	public int getAntiAir() {
		return antiAir;
	}

	public int getAntiInfantry() {
		return antiInfantry;
	}

	public int getAntiVehicle() {
		return antiVehicle;
	}

	public int getDefenseStructure() {
		return defenseStructure;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	@Override
	protected void collect(Structure s, List<Structure> neighbors) {
		if (s == null || s instanceof IsObstacle) {
			return;
		}
		
		if (s instanceof DefenseStructure) {
			defenseStructure++;
		} else if (s instanceof IsAntiAir) {
			antiAir++;
		} else if (s instanceof IsAntiVehicle) {
			antiVehicle++;
		} else if (s instanceof IsAntiInfantry) {
			antiInfantry++;
		}
	
	}
}
