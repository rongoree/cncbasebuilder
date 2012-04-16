package de.ganicoga.client.model.resource;

import de.ganicoga.client.model.IsResource;
import de.ganicoga.client.model.Refs;
import de.ganicoga.client.model.Structure;
import de.ganicoga.client.model.StructureDef;

public class Crystal extends Structure implements IsResource {

	public static final Crystal INSTANCE = new Crystal();
	
	private static int count = Refs.MAX_MINERALS;

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void incrementCount() {
		count++;
		if(count > Refs.MAX_MINERALS){
			count = Refs.MAX_MINERALS;
		}
	}

	@Override
	public void decrementCount() {
		count--;
		if(count < 0){
			count = 0;
		}
	}

	@Override
	protected StructureDef initStructure() {
		return StructureDef.CRYSTAL;
	}

	@Override
	public void reset() {
		count = Refs.MAX_MINERALS;
	}
}
