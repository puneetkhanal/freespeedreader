package com.freespeedreader.client.activities;

import com.freespeedreader.client.DictionaryServiceAsync;
import com.freespeedreader.client.VisitorServiceAsync;
import com.freespeedreader.client.view.IClientFactory;
import com.freespeedreader.client.view.IMainView;
import com.freespeedreader.client.view.MeaningPopup;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;

public class SpeedReadActivity extends AbstractActivity implements
		IMainView.Presenter {

	private final IClientFactory clientFactory;

	private DictionaryServiceAsync dictionaryServiceAsync;

	private IMainView mainView;

	public SpeedReadActivity(IClientFactory clientFactory, IMainView mainView,
			DictionaryServiceAsync dictionaryServiceAsync,
			VisitorServiceAsync visitorServiceAsync) {
		this.clientFactory = clientFactory;
		this.mainView = mainView;
		this.dictionaryServiceAsync = dictionaryServiceAsync;
		visitorServiceAsync.increment(new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Long result) {
				System.out.println(result);

			}
		});
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);

	}

	@Override
	public String lookupMeaning(String word) {
		final DialogBox dialogBox = new DialogBox(false, true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);
		dialogBox.setText("Wait");
		dialogBox.setWidget(new Label("Please wait loading word meaning..."));
		dialogBox.show();
		dialogBox.center();
		dictionaryServiceAsync.lookupMeaning(word, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				dialogBox.hide();
				MeaningPopup.showConfirmation("Exception", caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				dialogBox.hide();
				MeaningPopup.showConfirmation("Word Meaning", result);

			}
		});
		return null;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		mainView.setPresenter(this);
		panel.setWidget(mainView.asWidget());
	}

}
