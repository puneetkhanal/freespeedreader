package com.freespeedreader.client.view;

import com.freespeedreader.client.DictionaryServiceAsync;
import com.freespeedreader.client.VisitorServiceAsync;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface IClientFactory {

	DictionaryServiceAsync getDictionaryApi();

	EventBus getEventBus();

	IMainView getMainView();

	PlaceController getPlaceController();

	VisitorServiceAsync getVisitorApi();
}
