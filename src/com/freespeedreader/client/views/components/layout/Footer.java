package com.freespeedreader.client.views.components.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Footer extends Composite {

	interface FooterUiBinder extends UiBinder<Widget, Footer> {
	}

	private static FooterUiBinder uiBinder = GWT.create(FooterUiBinder.class);

	public Footer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
