package com.freespeedreader.client.views.components.layout;

import com.google.gwt.core.client.GWT;
//import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class Header extends Composite {

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

//	@UiField
//	ImageAnchor userName;
//
//	@UiField
//	ImageAnchor logoutButton;
//
//	@UiField
//	ImageAnchor loginButton;
//
//	@UiField
//	ImageAnchor registerButton;
//
//	@UiField
//	ImageAnchor settings;

//	@UiField
//	Image cccLogo;

//	PlaceController placeController;

	public Header() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void init() {
//		settings.getAnchor().addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				onSettingAnchorClicked();
//			}
//		});
	}

//	public void setPlaceController(PlaceController controller) {
//		this.placeController = controller;
//	}

	public void onSettingAnchorClicked() {
//		placeController.goTo(new SettingsPlace(""));
	}

	public void setUserName(String name) {
//		if (name == null || "".equals(name)) {
//			this.userName.setVisible(false);
//			this.loginButton.setVisible(true);
//			this.logoutButton.setVisible(false);
//			this.registerButton.setVisible(true);
//			this.settings.setVisible(false);
//		} else {
//			this.userName.setAnchorText(name);
//			this.loginButton.setVisible(false);
//			this.logoutButton.setVisible(true);
//			this.registerButton.setVisible(false);
//			this.settings.setVisible(true);
//		}

	}

}
