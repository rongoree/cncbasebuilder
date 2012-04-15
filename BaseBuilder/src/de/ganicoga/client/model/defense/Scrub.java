package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.IsObstacle;
import de.ganicoga.client.model.StructureDef;

public class Scrub extends DefenseStructure implements IsObstacle {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.SCRUB;
	}

}
