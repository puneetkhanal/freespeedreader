package com.freespeedreader.client.view;

import com.google.gwt.user.client.ui.ListBox;

public class FontSize extends ListBox {

	public FontSize() {
		setHeight("20px");
		for (int i = 1; i < 30; i++) {
			addItem(i + "", i + "");
		}
	}
}
