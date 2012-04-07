package de.ganicoga.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import de.ganicoga.client.presenter.AppPresenter;
import de.ganicoga.client.view.AppViewImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {

	private static ClientFactory cf;

	@Override
	public void onModuleLoad() {
		cf = GWT.create(ClientFactory.class);
		

		Resources.INSTANCE.style().ensureInjected();  
				
		new AppPresenter(new AppViewImpl()).go(RootLayoutPanel.get());

	}
	
	public static ClientFactory getClientFactory(){
		return cf;
	}

}
