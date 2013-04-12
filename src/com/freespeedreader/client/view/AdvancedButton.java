package com.freespeedreader.client.view;

import com.freespeedreader.client.resources.FreespeedreaderResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.HasSafeHtml;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;


public class AdvancedButton extends Composite implements HasClickHandlers, HasHTML, HasSafeHtml {

	interface AdvancedButtonUiBinder extends UiBinder<Widget, AdvancedButton> {
	}

	private static AdvancedButtonUiBinder uiBinder = GWT.create(AdvancedButtonUiBinder.class);

	@UiField
	PushButton button;

	@UiField
	Image image;

	@UiField(provided = true)
	FreespeedreaderResources resources;

	private boolean showProgressOnClick = false;

	private String text;

	public AdvancedButton() {
		resources =FreespeedreaderResources.INSTANCE;
		initWidget(uiBinder.createAndBindUi(this));
		image.setVisible(false);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return button.addClickHandler(handler);
	}

	public void disableAndShowProgress() {
		setEnabled(false);
		image.setVisible(true);
	}

	@Override
	public String getHTML() {
		return button.getHTML();
	}

	@Override
	public String getText() {
		return button.getText();
	}

	public boolean isEnabled() {
		return button.isEnabled();
	}

	public boolean isShowProgressOnClick() {
		return showProgressOnClick;
	}

	@UiHandler("button")
	public void onClick(ClickEvent event) {
		if (button.isEnabled()) {
			if (showProgressOnClick) {
				setEnabled(false);
				image.setVisible(true);
			}
			fireEvent(event);
		}
	}

	public void reset() {
		image.setVisible(false);
		setEnabled(true);
	}

	public void setEnabled(boolean value) {
		button.setEnabled(value);
		button.setText(text);
		image.setVisible(false);
	}

	public void setExplicitWidth(String value) {
		button.setWidth(value);
	}

	@Override
	public void setHTML(SafeHtml html) {
		setHTML(html.asString());
	}

	@Override
	public void setHTML(String html) {
		this.text = html;
		button.setHTML(html);
	}

	public void setLabel(String value) {
		this.text = value;
		button.setText(value);
	}

	public void setShowProgressOnClick(boolean showProgressOnClick) {
		this.showProgressOnClick = showProgressOnClick;
	}

	@Override
	public void setText(String text) {
		setLabel(text);
	}

}
