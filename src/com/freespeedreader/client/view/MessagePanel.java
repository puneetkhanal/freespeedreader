package com.freespeedreader.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class MessagePanel extends Composite {

	interface MessagePanelUiBinder extends UiBinder<Widget, MessagePanel> {
	}

	private static MessagePanelUiBinder uiBinder = GWT
			.create(MessagePanelUiBinder.class);

	@UiField
	public Button cancelButton;

	@UiField
	public Button okButton;

	@UiField
	public TextArea textArea;

	public MessagePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				// your commands here
				textArea.setVisible(true);
				textArea.setFocus(true);
				textArea.selectAll();
			}
		});
	}

	public String getText() {
		return textArea.getText();
	}

	public void setMessage(String html) {
	}

}
