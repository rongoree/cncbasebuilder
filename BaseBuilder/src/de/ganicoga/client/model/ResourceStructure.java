package de.ganicoga.client.model;


public abstract class ResourceStructure extends Structure implements HasLevel {
	
	private int level = 1;
	
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
}
