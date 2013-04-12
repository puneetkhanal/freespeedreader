package com.freespeedreader.client;

import com.freespeedreader.client.mvp.AppActivityMapper;
import com.freespeedreader.client.mvp.AppPlaceHistoryMapper;
import com.freespeedreader.client.places.SpeedReadPlace;
import com.freespeedreader.client.view.ClientFactory;
import com.freespeedreader.client.views.components.layout.Header;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Freespeedreader implements EntryPoint {

	interface Binder extends UiBinder<ScrollPanel, Freespeedreader> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	static final int width = 500;
	private SimplePanel appWidget = new SimplePanel();
	@UiField
	SimplePanel centerContainer;
	
	Context2d context;
	private Place defaultPlace = new SpeedReadPlace("main");

	@UiField
	Header header;
	String paragraph = "";
	private ScrollPanel scrollMain;

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		this.scrollMain = binder.createAndBindUi(this);
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		Window.enableScrolling(true);
		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		// Add it to the root panel.
		RootLayoutPanel.get().add(appWidget);
		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
		
	}
}
