package com.freespeedreader.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface IMainView extends IsWidget {

	public interface Presenter {
		void goTo(Place place);

		String lookupMeaning(String word);
	}

	void setPresenter(Presenter presenter);

}
