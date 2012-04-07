package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.StructureDef;

public class Wall extends DefenseStructure {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.WALL;
	}

}
