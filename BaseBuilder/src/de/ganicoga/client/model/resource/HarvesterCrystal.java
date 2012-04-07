package de.ganicoga.client.model.resource;

import de.ganicoga.client.model.StructureDef;

public class HarvesterCrystal extends Harvester {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.HARVESTER_CRYSTAL;
	}
}
