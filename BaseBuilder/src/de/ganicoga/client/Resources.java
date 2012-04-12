package de.ganicoga.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	//TODO public final static Resources INSTANCE = GWT.create(Resources.class);
	public final static Resources INSTANCE = GWT.create(Resources.class);

	public interface Style extends CssResource {

		String innerStyle();

		String rootStyle();

		String selectionBackground();

		String baseBackground();

		String acceptingTile();
		
		String rejectingTile();
		
		String selectedTile();
		
		String centered();
		
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
	ImageResource accumulator();

	@Source("resources/base/gdi/af.png")
	ImageResource airField();

	@Source("resources/base/gdi/br.png")
	ImageResource barracks();

	@Source("resources/base/gdi/cc.png")
	ImageResource commandCenter();

	@Source("resources/base/gdi/cy.png")
	ImageResource constructionYard();

	@Source("resources/base/gdi/df.png")
	ImageResource defenseFacility();

	@Source("resources/base/gdi/dhq.png")
	ImageResource defenseHQ();

	@Source("resources/base/gdi/fs.png")
	ImageResource falconSupport();

	@Source("resources/base/gdi/hv.png")
	ImageResource harvester();

	@Source("resources/base/gdi/hvc.png")
	ImageResource harvesterCrystal();

	@Source("resources/base/gdi/hvt.png")
	ImageResource harvesterTiberium();

	@Source("resources/base/gdi/is.png")
	ImageResource ionSupport();

	@Source("resources/base/gdi/pp.png")
	ImageResource powerPlant();

	@Source("resources/base/gdi/rf.png")
	ImageResource refinery();

	@Source("resources/base/gdi/s.png")
	ImageResource silo();

	@Source("resources/base/gdi/ss.png")
	ImageResource skySupport();

	@Source("resources/base/gdi/wf.png")
	ImageResource factory();
	
	
	//Defense Graphics
	@Source("resources/defense/common/ol.png")
	ImageResource oil();

	@Source("resources/defense/common/sc.png")
	ImageResource scrub();

	@Source("resources/defense/common/sw.png")
	ImageResource swamp();

	@Source("resources/defense/common/wd.png")
	ImageResource wood();

	@Source("resources/defense/gdi/w.png")
	ImageResource wall();

	@Source("resources/defense/gdi/wi.png")
	ImageResource wallInner();

	@Source("resources/defense/gdi/wl.png")
	ImageResource wallLeft();

	@Source("resources/defense/gdi/wr.png")
	ImageResource wallRight();

	@Source("resources/defense/common/oli.png")
	ImageResource oilInner();

	@Source("resources/defense/common/oll.png")
	ImageResource oilLeft();

	@Source("resources/defense/common/olr.png")
	ImageResource oilRight();

	@Source("resources/defense/gdi/at.png")
	ImageResource antiTankBarrier();

	@Source("resources/defense/gdi/gc.png")
	ImageResource guardianCannon();

	@Source("resources/defense/gdi/mg.png")
	ImageResource mgNest();

	@Source("resources/defense/gdi/ms.png")
	ImageResource missileSquad();

	@Source("resources/defense/gdi/pd.png")
	ImageResource predator();

	@Source("resources/defense/gdi/gd.png")
	ImageResource guardian();

	@Source("resources/defense/gdi/pb.png")
	ImageResource pitBull();

	

}
