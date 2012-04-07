package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.Obstacle;
import de.ganicoga.client.model.StructureDef;

public class Scrub extends DefenseStructure implements Obstacle {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.SCRUB;
	}

}
