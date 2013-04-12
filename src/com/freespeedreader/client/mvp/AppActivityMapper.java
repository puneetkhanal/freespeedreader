package com.freespeedreader.client.mvp;

import com.freespeedreader.client.activities.SpeedReadActivity;
import com.freespeedreader.client.places.SpeedReadPlace;
import com.freespeedreader.client.view.IClientFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


public class AppActivityMapper implements ActivityMapper {

	private final IClientFactory clientFactory;


	public AppActivityMapper(IClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(final Place place) {
		if(place instanceof SpeedReadPlace){
			return new SpeedReadActivity(clientFactory,clientFactory.getMainView(),clientFactory.getDictionaryApi(),clientFactory.getVisitorApi());
		}
		return null;
	}

}