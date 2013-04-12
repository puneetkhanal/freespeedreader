package com.freespeedreader.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;

public class MeaningPopup {

	private static boolean showHide = false;

	private static String text;

	public static void showConfirmation(String title, String message) {
		showConfirmation(title, message, null);
	}

	public static void showConfirmation(String title, String message,
			boolean showhide) {
		showHide = showhide;
		showConfirmation(title, message, null);
	}

	public static void showConfirmation(String title, String message,
			final IMessageBoxHandler handler) {

		final DialogBox dialogBox = new DialogBox(false, true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);
		dialogBox.setText(title);

		final MeaningPanel mPanel = new MeaningPanel();
		mPanel.setMessage(message);

		mPanel.close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();

			}
		});

		dialogBox.setWidget(mPanel);
		dialogBox.center();

	}

	public String getText() {
		return text;
	}

	public void sourceCodeUpload() {

	}

}
