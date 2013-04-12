package com.freespeedreader.client.view;

import com.freespeedreader.client.DictionaryService;
import com.freespeedreader.client.DictionaryServiceAsync;
import com.freespeedreader.client.VisitorService;
import com.freespeedreader.client.VisitorServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactory implements IClientFactory {

	private DictionaryServiceAsync dictionaryServiceAsync;
	private final EventBus eventBus = new SimpleEventBus();
	private IMainView mainView;
	private PlaceController placeController = new PlaceController(eventBus);
	private VisitorServiceAsync visitorServiceAsync;

	public ClientFactory() {
		dictionaryServiceAsync = GWT.create(DictionaryService.class);
		visitorServiceAsync = GWT.create(VisitorService.class);
	}

	@Override
	public DictionaryServiceAsync getDictionaryApi() {
		return dictionaryServiceAsync;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public IMainView getMainView() {
		if (mainView == null) {
			mainView = new MainView();
			return mainView;
		} else {
			return mainView;
		}
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public VisitorServiceAsync getVisitorApi() {
		return visitorServiceAsync;
	}

	public void setPlaceController(PlaceController placeController) {
		this.placeController = placeController;
	}

}
