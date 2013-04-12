package com.freespeedreader.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface FreespeedreaderResources extends ClientBundle {

	public static final FreespeedreaderResources INSTANCE = GWT
			.create(FreespeedreaderResources.class);

	@Source("clock.png")
	ImageResource iconClock();

	@Source("decrease1.png")
	ImageResource iconDecreaseWidth();

	@Source("down1.png")
	ImageResource iconDown();

	@Source("downarrow1.png")
	ImageResource iconDownArrow();

	@Source("forward.png")
	ImageResource iconForward();

	@Source("increase1.png")
	ImageResource iconIncreaseWidth();

	@Source("logo.png")
	ImageResource iconLogo();

	@Source("minus1.png")
	ImageResource iconMinus();

	@Source("next1.png")
	ImageResource iconNext();

	@Source("paste.png")
	ImageResource iconPaste();

	@Source("pause1.png")
	ImageResource iconPause();

	@Source("plus1.png")
	ImageResource iconPlus();
	
	@Source("previous1.png")
	ImageResource iconPrevious();
	
	@Source("settings.png")
	ImageResource iconSettings();
	
	@Source("start1.png")
	ImageResource iconStart();
	@Source("stop1.png")
	ImageResource iconStop();
	@Source("icon_timer.png")
	ImageResource iconTimer();
	@Source("up1.png")
	ImageResource iconUp();

}
