package com.freespeedreader.client.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MeaningPanel extends Composite {

	interface MeaningPanelUiBinder extends UiBinder<Widget, MeaningPanel> {
	}

	private static MeaningPanelUiBinder uiBinder = GWT.create(MeaningPanelUiBinder.class);


	@UiField
	public Button close;
	
	@UiField
	public HTML messageLabel;

	public MeaningPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setMessage(String html) {
		messageLabel.setHTML(html);
	}

}
