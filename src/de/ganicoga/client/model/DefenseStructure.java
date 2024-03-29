package de.ganicoga.client.model;

public abstract class DefenseStructure extends Structure implements HasLevel {

	private int level;
	
	@Override
	public void reset(){
	}

	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public void incrementCount() {
	}
	
	@Override
	public void decrementCount() {
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return level;
	}
}
