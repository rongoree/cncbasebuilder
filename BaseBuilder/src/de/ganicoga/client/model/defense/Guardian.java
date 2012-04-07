package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.Obstacle;
import de.ganicoga.client.model.StructureDef;

public class Guardian extends DefenseStructure implements Obstacle {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.GUARDIAN;
	}

}
