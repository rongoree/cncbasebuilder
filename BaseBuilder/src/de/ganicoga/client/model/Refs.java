package de.ganicoga.client.model;

public class Refs {

	public static final String VERSION_NUMBER = "0.6.1";
	public static final int MAX_MINERALS = 7;
	public static final int MAX_LEVEL = 50;
	private static final int MAX_TABLE_LEVEL = 12;

	/**
	 * An array of minute values for each level.
	 */
	private static final double[] TIME_STEP_TABLE = new double[] { 0, 1, 2,
			10 / 3, 10, 20, 30, 60, 120, 150, 180, 240, 360 };

	private static final int[] MINERAL_TABLE = new int[] { 0, 3, 8, 19, 78,
			200, 373, 907, 2147, 3167, 4440, 6933, 12100 };

	private static final int[] INFINITE_TABLE = new int[] { 0, 2, 5, 12, 47,
			120, 224, 544, 1288, 1900, 2664, 4160, 7260 };

	/**
	 * Used by </br>Silo(tiberium, crystal) linked to Harvester
	 */
	private static final int[] CONTINUOUS_1 = new int[] { 0, 33, 47, 69, 93,
			120, 149, 181, 215, 253, 296, 347, 403 };

	/**
	 * Used by </br>Accumulator(power) linked to PowerPlant,
	 * </br>PowerPlant(credits) linked to Refinery
	 */
	private static final int[] CONTINUOUS_2 = new int[] { 0, 40, 56, 83, 112,
			144, 179, 218, 258, 304, 355, 416, 484 };

	/**
	 * Used by </br>Harvester(tiberium, crystal) linked to Silo,
	 * </br>PowerPlant(power) linked to Crystal, </br>Refinery(credits) linked
	 * to Tiberium
	 */
	private static final int[] CONTINUOUS_3 = new int[] { 0, 50, 70, 104, 140,
			180, 224, 272, 322, 380, 444, 520, 605 };

	/**
	 * Used by </br>PowerPlant(power) linked to Accumulator,
	 * </br>Refinery(credits) linked to PowerPlant)
	 */
	private static final int[] CONTINUOUS_4 = new int[] { 0, 60, 84, 125, 168,
			216, 269, 326, 386, 456, 533, 624, 726 };

	public static final double getTimeStep(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return TIME_STEP_TABLE[level];
		}
		else{
			return TIME_STEP_TABLE[MAX_TABLE_LEVEL];
		}
		
	}

	public static final double getMineralPackageAmount(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return MINERAL_TABLE[level];
		}
		else{
			return (int) Math
					.floor((MINERAL_TABLE[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}
	}

	public static final double getInfinitePackageAmount(int level) {		
		if(level <= MAX_TABLE_LEVEL){
			return INFINITE_TABLE[level];
		}
		else{
			return (int) Math
					.floor((INFINITE_TABLE[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}
	}

	/**
	 * Calculates the package production of a Harvester for Crystal/Tiberium per
	 * hour.
	 * 
	 * @param level
	 *            The level of the Harvester.
	 * @return The mineral production per hour of an Harvester at the specified
	 *         level.
	 */
	public static final double getMineralPackageProduction(int level) {
		return 60 / getTimeStep(level) * getMineralPackageAmount(level);
	}

	/**
	 * Calculates the package production of a PowerPlant for Power or of a
	 * Refinery for Credits per hour.
	 * 
	 * @param level
	 *            The level of the PowerPlant or Refinery.
	 * @return The Credit/Power production per hour of a Refinery/PowerPlant at
	 *         the specified level.
	 */
	public static final double getInfinitePackageProduction(int level) {
		return 60 / getTimeStep(level) * getInfinitePackageAmount(level);
	}

	/**
	 * Calculates the continuous production of a Resource per hour.</br>
	 * 
	 * Used by </br>Silo(tiberium, crystal) linked to Harvester
	 * 
	 * @param level
	 *            The level of the producing structure.
	 * @return The continuous resource production per hour of the producing
	 *         structure at the specified level.
	 */
	public static final int getContinous1Production(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return CONTINUOUS_1[level];
		}
		else{
			return (int) Math
					.floor((CONTINUOUS_1[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}	
	}

	/**
	 * Calculates the continuous production of a Resource per hour.</br>
	 * 
	 * Used by </br>Accumulator(power) linked to PowerPlant,
	 * </br>PowerPlant(credits) linked to Refinery
	 * 
	 * @param level
	 *            The level of the producing structure.
	 * @return The continuous resource production per hour of the producing
	 *         structure at the specified level.
	 */
	public static final int getContinous2Production(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return CONTINUOUS_2[level];
		}
		else{
			return (int) Math
					.floor((CONTINUOUS_2[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}
	}

	/**
	 * Calculates the continuous production of a Resource per hour.</br>
	 * 
	 * Used by </br>Harvester(tiberium, crystal) linked to Silo,
	 * </br>PowerPlant(power) linked to Crystal, </br>Refinery(credits) linked
	 * to Tiberium
	 * 
	 * @param level
	 *            The level of the producing structure.
	 * @return The continuous resource production per hour of the producing
	 *         structure at the specified level.
	 */
	public static final int getContinous3Production(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return CONTINUOUS_3[level];
		}
		else{
			return (int) Math
					.floor((CONTINUOUS_3[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}
	}

	/**
	 * Calculates the continuous production of a Resource per hour.</br>
	 * 
	 * Used by </br>PowerPlant(power) linked to Accumulator,
	 * </br>Refinery(credits) linked to PowerPlant)
	 * 
	 * @param level
	 *            The level of the producing structure.
	 * @return The continuous resource production per hour of the producing
	 *         structure at the specified level.
	 */
	public static final int getContinous4Production(int level) {
		if(level <= MAX_TABLE_LEVEL){
			return CONTINUOUS_4[level];
		}
		else{
			return (int) Math
					.floor((CONTINUOUS_4[MAX_TABLE_LEVEL] * Math.pow(1.2, level - MAX_TABLE_LEVEL)));
		}
	}
}
