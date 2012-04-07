package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.StructureDef;

public class WatchTower extends DefenseStructure {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.WATCHTOWER;
	}

}
