package de.ganicoga.client.model.resource;

import de.ganicoga.client.model.ResourceStructure;
import de.ganicoga.client.model.StructureDef;

public class Silo extends ResourceStructure {

	@Override
	protected StructureDef initStructure() {
		return StructureDef.SILO;
	}

}
