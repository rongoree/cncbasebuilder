package de.ganicoga.client.model;


public abstract class UniqueStructure extends Structure implements HasLevel {

	protected int level;

	@Override
	public void setLevel(int level) {
		this.level = level;
		
	}

	@Override
	public int getLevel() {
		return level;
	}
	
	@Override
	public void reset() {
		this.incrementCount();
	}
}