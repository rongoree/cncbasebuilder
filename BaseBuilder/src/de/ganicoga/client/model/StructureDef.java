package de.ganicoga.client.model;

import com.google.gwt.resources.client.ImageResource;

import de.ganicoga.client.Resources;

public enum StructureDef {

	TIBERIUM("Tiberum", "foobar", Resources.INSTANCE.tiberiumField(), 1), 
	CRYSTAL("Crystal", "foobar", Resources.INSTANCE.crystalField(), 2),
	
	HARVESTER("Harvester", "foobar", Resources.INSTANCE.harvester(), 3), 
	HARVESTER_TIBERIUM("Harvester", "foobar", Resources.INSTANCE.harvesterTiberium(), 4), 
	HARVESTER_CRYSTAL("Harvester", "foobar", Resources.INSTANCE.harvesterCrystal(), 5), 
	CONSTRUCTION_YARD("Construction Yard", "foobar", Resources.INSTANCE.constructionYard(), 6),
	POWER_PLANT("Power Plant", "foobar", Resources.INSTANCE.powerPlant(), 7), 
	REFINERY("Refinery", "foobar", Resources.INSTANCE.refinery(), 8), 
	SILO("Silo", "foobar", Resources.INSTANCE.silo(), 9), 
	ACCUMULATOR("Accumulator", "foobar", Resources.INSTANCE.accumulator(), 10),
	COMMAND_CENTER("Command Center", "foobar",Resources.INSTANCE.commandCenter(), 11), 
	FACTORY("Factory","foobar", Resources.INSTANCE.factory(), 12), 
	BARRACKS("Barracks","foobar", Resources.INSTANCE.barracks(), 13),
	AIR_FIELD("Airfield","foobar", Resources.INSTANCE.airField(), 14), 
	DEFENSE_HQ("Defense Headquarter", "foobar", Resources.INSTANCE.defenseHQ(), 15), 
	DEFENSE_FACILITY("Defense Facility", "foobar", Resources.INSTANCE.defenseFacility(),16), 
	SKY_SUPPORT("Skystrike Support", "foobar", Resources.INSTANCE.skySupport(), 17),
	FALCON_SUPPORT("Falcon Support", "foobar",Resources.INSTANCE.falconSupport(), 18),
	ION_SUPPORT("Ion Support","foobar", Resources.INSTANCE.ionSupport(), 19),
	
	WALL("Wall","foobar", Resources.INSTANCE.wall(), 20),
	MG_NEST("MG Nest","foobar", Resources.INSTANCE.ionSupport(), 21),
	PREDATOR("Predator","foobar", Resources.INSTANCE.ionSupport(), 22),
	MISSILE_SQUAD("Missile Squad","foobar", Resources.INSTANCE.ionSupport(), 23),
	AT_BARRIER("Anti-Tank Barrier","foobar", Resources.INSTANCE.ionSupport(), 24),
	GUARDIAN("Guardian","foobar", Resources.INSTANCE.ionSupport(), 25),
	GUARDIAN_CANNON("Guardian Cannon","foobar", Resources.INSTANCE.ionSupport(), 26),
	PITBULL("Pitbull","foobar", Resources.INSTANCE.ionSupport(), 27),
	BARBWIRE("Barbwire","foobar", Resources.INSTANCE.ionSupport(), 28),
	SNIPER_TEAM("Sniper Team","foobar", Resources.INSTANCE.ionSupport(), 29),
	FLAK("Flak","foobar", Resources.INSTANCE.ionSupport(), 30),
	ZONE_TROOPER("Zone Trooper","foobar", Resources.INSTANCE.ionSupport(), 31),
	WATCHTOWER("Watchtower","foobar", Resources.INSTANCE.ionSupport(), 32),
	TITAN_ARTILLERY("Titan Artillery","foobar", Resources.INSTANCE.ionSupport(), 33),
	SAM_SITE("SAM Site","foobar", Resources.INSTANCE.ionSupport(), 34),
	
	WOODS("Woods","foobar", Resources.INSTANCE.wood(), 35),
	OIL_SLICK("Oil Slick","foobar", Resources.INSTANCE.oil(), 36),
	SCRUB("Scrub","foobar", Resources.INSTANCE.scrub(), 37),
	SWAMP("Swamp","foobar", Resources.INSTANCE.swamp(), 38);
	
	private String name;
	private String description;
	private ImageResource image;
	private int id;

	StructureDef(String name, String description, ImageResource image, int id){
		this.name = name;
		this.description = description;
		this.image = image;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ImageResource getImageResource() {
		return image;
	}

	public int getId() {
		return id;
	}
}
