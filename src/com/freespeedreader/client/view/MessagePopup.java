package com.freespeedreader.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;

public class MessagePopup {

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

		final MessagePanel mPanel = new MessagePanel();
		mPanel.setMessage(message);

		mPanel.okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (handler != null) {
					System.out.println(mPanel.getText());
					text = mPanel.getText();
					handler.onConfirmed(text);

				}
				dialogBox.hide();

			}
		});

		mPanel.cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				dialogBox.hide();
			}
		});

		dialogBox.setWidget(mPanel);
		dialogBox.center();

		if (showHide) {
			mPanel.cancelButton.setVisible(false);
			showHide = false;
		}

	}

	public String getText() {
		return text;
	}

	public void sourceCodeUpload() {

	}

}
