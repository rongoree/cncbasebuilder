package de.ganicoga.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {

	public final static Resources INSTANCE = GWT.create(Resources.class);

	public interface Style extends CssResource {

		String rootStyle();

		String selectionBackground();

		String baseBackground();

		String acceptingTile();
		
		String rejectingTile();
		
		String selectedTile();
		
		String topPanel();
		
		String statsStyle();
		
		String whiteText();

	}
	
	//CSS File
	@Source("resources/style.css")
	Style style();

	@Source("resources/ground.png")
	ImageResource placedTileBG();

	@Source("resources/acceptingTile.png")
	ImageResource acceptingTile();
	
	@Source("resources/rejectingTile.png")
	ImageResource rejectingTile();
	
	@Source("resources/selectedTile.png")
	ImageResource selectedTile();
	
	@Source("resources/selectionTile.png")
	ImageResource tileSelectorBG();
	
	@Source("resources/acceptingTile.png")
	ImageResource acceptedTile();

	//Base Graphics
	@Source("resources/base/common/cf.png")
	ImageResource crystalField();

	@Source("resources/base/common/tf.png")
	ImageResource tiberiumField();

	@Source("resources/base/gdi/ac.png")
	ImageResource accumulator_gdi();
	@Source("resources/base/gdi/ac.png")
	ImageResource accumulator_nod();

	@Source("resources/base/gdi/af.png")
	ImageResource airField_gdi();
	@Source("resources/base/gdi/af.png")
	ImageResource airField_nod();

	@Source("resources/base/gdi/br.png")
	ImageResource barracks_gdi();
	@Source("resources/base/gdi/br.png")
	ImageResource barracks_nod();

	@Source("resources/base/gdi/cc.png")
	ImageResource commandCenter_gdi();
	@Source("resources/base/gdi/cc.png")
	ImageResource commandCenter_nod();

	@Source("resources/base/gdi/cy.png")
	ImageResource constructionYard_gdi();
	@Source("resources/base/gdi/cy.png")
	ImageResource constructionYard_nod();

	@Source("resources/base/gdi/df.png")
	ImageResource defenseFacility_gdi();
	@Source("resources/base/gdi/df.png")
	ImageResource defenseFacility_nod();

	@Source("resources/base/gdi/dhq.png")
	ImageResource defenseHQ_gdi();
	@Source("resources/base/gdi/dhq.png")
	ImageResource defenseHQ_nod();

	@Source("resources/base/gdi/fs.png")
	ImageResource falconSupport_gdi();
	@Source("resources/base/gdi/fs.png")
	ImageResource falconSupport_nod();

	@Source("resources/base/gdi/hv.png")
	ImageResource harvester_gdi();
	@Source("resources/base/gdi/hv.png")
	ImageResource harvester_nod();

	@Source("resources/base/gdi/hvc.png")
	ImageResource harvesterCrystal_gdi();
	@Source("resources/base/gdi/hvc.png")
	ImageResource harvesterCrystal_nod();

	@Source("resources/base/gdi/hvt.png")
	ImageResource harvesterTiberium_gdi();
	@Source("resources/base/gdi/hvt.png")
	ImageResource harvesterTiberium_nod();

	@Source("resources/base/gdi/is.png")
	ImageResource ionSupport_gdi();
	@Source("resources/base/gdi/is.png")
	ImageResource ionSupport_nod();

	@Source("resources/base/gdi/pp.png")
	ImageResource powerPlant_gdi();
	@Source("resources/base/gdi/pp.png")
	ImageResource powerPlant_nod();

	@Source("resources/base/gdi/rf.png")
	ImageResource refinery_gdi();
	@Source("resources/base/gdi/rf.png")
	ImageResource refinery_nod();

	@Source("resources/base/gdi/s.png")
	ImageResource silo_gdi();
	@Source("resources/base/gdi/s.png")
	ImageResource silo_nod();
	
	@Source("resources/base/gdi/ss.png")
	ImageResource skySupport_gdi();
	@Source("resources/base/gdi/ss.png")
	ImageResource skySupport_nod();

	@Source("resources/base/gdi/wf.png")
	ImageResource factory_gdi();
	@Source("resources/base/gdi/wf.png")
	ImageResource factory_nod();
	
	
	//Defense Graphics
	@Source("resources/defense/common/ol.png")
	ImageResource oil();

	@Source("resources/defense/common/sc.png")
	ImageResource scrub();

	@Source("resources/defense/common/sw.png")
	ImageResource swamp();

	@Source("resources/defense/common/wd.png")
	ImageResource wood();
	
	@Source("resources/defense/common/oli.png")
	ImageResource oilInner();

	@Source("resources/defense/common/oll.png")
	ImageResource oilLeft();

	@Source("resources/defense/common/olr.png")
	ImageResource oilRight();

	@Source("resources/defense/gdi/w.png")
	ImageResource wall_gdi();

	@Source("resources/defense/gdi/wi.png")
	ImageResource wallInner_gdi();

	@Source("resources/defense/gdi/wl.png")
	ImageResource wallLeft_gdi();

	@Source("resources/defense/gdi/wr.png")
	ImageResource wallRight_gdi();

	@Source("resources/defense/gdi/at.png")
	ImageResource antiTankBarrier_gdi();

	@Source("resources/defense/gdi/gc.png")
	ImageResource guardianCannon_gdi();

	@Source("resources/defense/gdi/mg.png")
	ImageResource mgNest_gdi();

	@Source("resources/defense/gdi/ms.png")
	ImageResource missileSquad_gdi();

	@Source("resources/defense/gdi/pd.png")
	ImageResource predator_gdi();

	@Source("resources/defense/gdi/gd.png")
	ImageResource guardian_gdi();

	@Source("resources/defense/gdi/pb.png")
	ImageResource pitBull_gdi();

	@Source("resources/defense/gdi/bw.png")
	ImageResource barbWire_gdi();

	@Source("resources/defense/gdi/st.png")
	ImageResource sniperTeam_gdi();

	@Source("resources/defense/gdi/ta.png")
	ImageResource titanArty_gdi();

	@Source("resources/defense/gdi/zt.png")
	ImageResource zoneTrooper_gdi();

	

}
