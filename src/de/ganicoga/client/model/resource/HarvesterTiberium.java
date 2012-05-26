package de.ganicoga.client.model.resource;

import de.ganicoga.client.model.StructureDef;

public class HarvesterTiberium extends Harvester {

	public static final HarvesterTiberium INSTANCE = new HarvesterTiberium();

	@Override
	protected StructureDef initStructure() {
		return StructureDef.HARVESTER_TIBERIUM;
	}
}
