package de.ganicoga.client.model.defense;

import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.StructureDef;

public class Barbwire extends DefenseStructure {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.BARBWIRE;
	}

}
