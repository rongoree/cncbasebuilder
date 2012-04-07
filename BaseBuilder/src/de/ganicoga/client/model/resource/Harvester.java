package de.ganicoga.client.model.resource;

import de.ganicoga.client.model.ResourceStructure;
import de.ganicoga.client.model.StructureDef;

public class Harvester extends ResourceStructure {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.HARVESTER;
	}
}
