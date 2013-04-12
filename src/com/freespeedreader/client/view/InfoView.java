package com.freespeedreader.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InfoView extends Composite {

	interface InfoViewUiBinder extends UiBinder<Widget, InfoView> {
	}

	private static InfoViewUiBinder uiBinder = GWT
			.create(InfoViewUiBinder.class);

	public InfoView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public InfoView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
