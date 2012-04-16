package de.ganicoga.client.model;

import com.google.gwt.resources.client.ImageResource;

public abstract class Structure {
	
	protected StructureDef def;
	
	public Structure(){
		def = initStructure();
	}

	public ImageResource getImageResource(){
		return def.getImageResource();
	}

	public String getName(){
		return def.getName();
	}

	public String getDescription(){
		return def.getDescription();
	}

	public int getId(){
		return def.getId();
	}

	abstract public int getCount();
	abstract public void incrementCount();
	abstract public void decrementCount();
	abstract public void reset();
	
	abstract protected StructureDef initStructure();
	
	public String toString(){
		return def.getName();
	}


}
