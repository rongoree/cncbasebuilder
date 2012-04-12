package de.ganicoga.client.model;

import java.util.List;

import de.ganicoga.client.model.resource.Accumulator;
import de.ganicoga.client.model.resource.Crystal;
import de.ganicoga.client.model.resource.HarvesterCrystal;
import de.ganicoga.client.model.resource.HarvesterTiberium;
import de.ganicoga.client.model.resource.PowerPlant;
import de.ganicoga.client.model.resource.Refinery;
import de.ganicoga.client.model.resource.Silo;
import de.ganicoga.client.model.resource.Tiberium;

public class BaseModel extends AbstractBaseModel {

	private int continuousTiberium, continuousCrystal, continuousPower,
			continuousCredits;
	private double packageTiberium, packageCrystal, packagePower,
			packageCredits;
	private int buildings;

	public BaseModel(String token) {
		super(token);
	}

	public BaseModel() {
		super();
	}
	
	public void update(){
		collectAll();
	}

	@Override
	protected void collectAll() {

		continuousTiberium = 0;
		continuousCrystal = 0;
		continuousPower = 0;
		continuousCredits = 0;

		packageTiberium = 0;
		packageCrystal = 0;
		packagePower = 0;
		packageCredits = 0;

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
		if (s instanceof ResourceStructure) {
			ResourceStructure rs = (ResourceStructure) s;

			if (rs instanceof HarvesterTiberium) {
				packageTiberium += Refs.getMineralPackageProduction(rs
						.getLevel());
				boolean siloUsedUp = false;
				for (Structure n : neighbors) {
					if (n instanceof Silo && !siloUsedUp) {
						continuousTiberium += Refs.getContinous3Production(rs
								.getLevel());
						siloUsedUp = true;
					}
				}
			} else if (rs instanceof HarvesterCrystal) {
				packageCrystal += Refs.getMineralPackageProduction(rs
						.getLevel());
				boolean siloUsedUp = false;
				for (Structure n : neighbors) {
					if (n instanceof Silo && !siloUsedUp) {
						continuousCrystal += Refs.getContinous3Production(rs
								.getLevel());
						siloUsedUp = true;
					}
				}
			} else if (rs instanceof PowerPlant) {
				packagePower += Refs
						.getInfinitePackageProduction(rs.getLevel());
				boolean accumulatorUsedUp = false;
				for (Structure n : neighbors) {
					if (n instanceof Crystal || n instanceof HarvesterCrystal) {
						continuousPower += Refs.getContinous3Production(rs
								.getLevel());
					} else if (n instanceof Accumulator && !accumulatorUsedUp) {
						continuousPower += Refs.getContinous4Production(rs
								.getLevel());
						accumulatorUsedUp = true;
					} else if (n instanceof Refinery) {
						continuousCredits += Refs.getContinous2Production(rs
								.getLevel());
					}
				}
			} else if (rs instanceof Refinery) {
				packageCredits += Refs.getInfinitePackageProduction(rs
						.getLevel());
				boolean powerPlantUsedUp = false;
				for (Structure n : neighbors) {
					if (n instanceof Tiberium || n instanceof HarvesterTiberium) {
						continuousCredits += Refs.getContinous3Production(rs
								.getLevel());
					} else if (n instanceof PowerPlant && !powerPlantUsedUp) {
						continuousCredits += Refs.getContinous4Production(rs
								.getLevel());
						powerPlantUsedUp = true;
					}
				}
			}
			else if(rs instanceof Silo){
				for (Structure n : neighbors) {
					if (n instanceof HarvesterTiberium) {
						continuousTiberium += Refs.getContinous1Production(rs.getLevel());
					}
					else if (n instanceof HarvesterCrystal) {
						continuousCrystal += Refs.getContinous1Production(rs.getLevel());
					}
				}
			}
			else if(rs instanceof Accumulator){
				for (Structure n : neighbors) {
					if (n instanceof PowerPlant) {
						continuousPower += Refs.getContinous2Production(rs.getLevel());
					}
				}
			}
		}
	}

	public int getContinuousTiberium() {
		return continuousTiberium;
	}

	public int getContinuousCrystal() {
		return continuousCrystal;
	}

	public int getContinuousPower() {
		return continuousPower;
	}

	public int getContinuousCredits() {
		return continuousCredits;
	}

	public int getPackageTiberium() {
		return (int) packageTiberium;
	}

	public int getPackageCrystal() {
		return (int) packageCrystal;
	}

	public int getPackagePower() {
		return (int) packagePower;
	}

	public int getPackageCredits() {
		return (int) packageCredits;
	}

	public int getBuildings() {
		return buildings;
	}

}
