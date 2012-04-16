package de.ganicoga.client.model.unique;

import de.ganicoga.client.model.StructureDef;
import de.ganicoga.client.model.UniqueStructure;

public class AirField extends UniqueStructure {
	
	public static final AirField INSTANCE = new AirField();
	private static int count = 1;
	
	@Override
	public void incrementCount() {
		if (++count > 1) {
			count = 1;
		}
	}

	@Override
	public void decrementCount() {
	if (--count < 0) {
			count = 0;
		}
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	protected StructureDef initStructure() {
		return StructureDef.AIR_FIELD;		
	}
}
