package de.ganicoga.client.model;

import com.google.gwt.resources.client.ImageResource;

import static de.ganicoga.client.Resources.INSTANCE;

public enum StructureDef {

	TIBERIUM(new String[] { "Tiberum" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.tiberiumField() }, 1), CRYSTAL(
			new String[] { "Crystal" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.crystalField(), }, 2),

	HARVESTER(new String[] { "Harvester" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.harvester_gdi() }, 3), HARVESTER_TIBERIUM(
			new String[] { "Harvester" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.harvesterTiberium_gdi() }, 4), HARVESTER_CRYSTAL(
			new String[] { "Harvester" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.harvesterCrystal_gdi() }, 5), CONSTRUCTION_YARD(
			new String[] { "Construction Yard" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.constructionYard_gdi() }, 6), POWER_PLANT(
			new String[] { "Power Plant" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.powerPlant_gdi() }, 7), REFINERY(
			new String[] { "Refinery" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.refinery_gdi() }, 8), SILO(
			new String[] { "Silo" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.silo_gdi() }, 9), ACCUMULATOR(
			new String[] { "Accumulator" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.accumulator_gdi() }, 10), COMMAND_CENTER(
			new String[] { "Command Center" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.commandCenter_gdi() }, 11), FACTORY(
			new String[] { "Factory" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.factory_gdi() }, 12), BARRACKS(
			new String[] { "Barracks" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.barracks_gdi() }, 13), AIR_FIELD(
			new String[] { "Airfield" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.airField_gdi() }, 14), DEFENSE_HQ(
			new String[] { "Defense Headquarter" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.defenseHQ_gdi() }, 15), DEFENSE_FACILITY(
			new String[] { "Defense Facility" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.defenseFacility_gdi() }, 16), SKY_SUPPORT(
			new String[] { "Skystrike Support" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.skySupport_gdi() }, 17), FALCON_SUPPORT(
			new String[] { "Falcon Support" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.falconSupport_gdi() }, 18), ION_SUPPORT(
			new String[] { "Ion Support" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.ionSupport_gdi() }, 19),

	WALL(new String[] { "Wall" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.wall_gdi() }, 20), MG_NEST(
			new String[] { "MG Nest" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.mgNest_gdi() }, 21), PREDATOR(
			new String[] { "Predator" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.predator_gdi() }, 22), MISSILE_SQUAD(
			new String[] { "Missile Squad" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.missileSquad_gdi() }, 23), AT_BARRIER(
			new String[] { "Anti-Tank Barrier" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.antiTankBarrier_gdi() }, 24), GUARDIAN(
			new String[] { "Guardian" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.guardian_gdi() }, 25), GUARDIAN_CANNON(
			new String[] { "Guardian Cannon" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.guardianCannon_gdi() }, 26), PITBULL(
			new String[] { "Pitbull" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.pitBull_gdi() }, 27), BARBWIRE(
			new String[] { "Barbwire" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.barbWire_gdi() }, 28), SNIPER_TEAM(
			new String[] { "Sniper Team" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.sniperTeam_gdi() }, 29), FLAK(
			new String[] { "Flak" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.ionSupport_gdi() }, 30), ZONE_TROOPER(
			new String[] { "Zone Trooper" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.zoneTrooper_gdi() }, 31), WATCHTOWER(
			new String[] { "Watchtower" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.ionSupport_gdi() }, 32), TITAN_ARTILLERY(
			new String[] { "Titan Artillery" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.titanArty_gdi() }, 33), SAM_SITE(
			new String[] { "SAM Site" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.ionSupport_gdi() }, 34),

	WOODS(new String[] { "Woods" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.wood() }, 35), OIL_SLICK(
			new String[] { "Oil Slick" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.oil() }, 36), SCRUB(
			new String[] { "Scrub" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.scrub() }, 37), SWAMP(
			new String[] { "Swamp" }, new String[] { "foobar" },
			new ImageResource[] { INSTANCE.swamp() }, 38);

	private String[] name;
	private String[] description;
	private ImageResource[] image;
	private int id;
	private int activeFaction = 0;

	StructureDef(String[] name, String[] description, ImageResource[] image,
			int id) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.id = id;
	}

	public String getName() {
		return name.length > activeFaction ? name[activeFaction] : name[0];
	}

	public String getDescription() {
		return description.length > activeFaction ? description[activeFaction]
				: description[0];
	}

	public ImageResource getImageResource() {
		return image.length > activeFaction ? image[activeFaction] : image[0];
	}

	public int getId() {
		return id;
	}

	public void setActiveFaction(int activeFaction) {
		this.activeFaction = activeFaction;
	}
}
