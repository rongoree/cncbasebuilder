package de.ganicoga.client.model;

public class Refs {
	
	public static final String VERSION_NUMBER = "0.6";
	
	public static final int[] MINERAL_TABLE = new int[] { 0, 80, 100, 120, 140,
			200, 480, 760, 1050, 1250, 1500, 1800, 2160 };
	
	public static final int[] INFINITE_TABLE = new int[] { 0, 65, 80, 95, 110,
			160, 380, 610, 840, 1000, 1200, 1440, 1730 };

	public static final double SPEED_BONUS = 0.25d;
	public static final double MAX_SPEED_BONUS = 1.0d;
	
	public static final int MAX_MINERALS = 7;

	public static final int mineralFn(int level) {
		return (int) Math
				.floor((MINERAL_TABLE[12] * Math.pow(1.2, level - 12)));
	}

	public static final int infiniteFn(int level) {
		return (int) Math
				.floor((INFINITE_TABLE[12] * Math.pow(1.2, level - 12)));
	}

}
