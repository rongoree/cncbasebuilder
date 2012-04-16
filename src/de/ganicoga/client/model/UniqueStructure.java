package de.ganicoga.client.model;


public abstract class UniqueStructure extends Structure implements HasLevel {

	protected int level = 1;

	@Override
	public void setLevel(int level) {
		if(level > Refs.MAX_LEVEL){
			level = Refs.MAX_LEVEL;
		}
		else if(level < 1){
			level = 1;
		}
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