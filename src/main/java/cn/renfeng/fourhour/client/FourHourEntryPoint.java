package cn.renfeng.fourhour.client;

import cn.renfeng.fourhour.client.ui.ArtistList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class FourHourEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(new ArtistList());
	}

}
