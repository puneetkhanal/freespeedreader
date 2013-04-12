package com.freespeedreader.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Font picker
 */
public class FontPicker extends PopupPanel implements ClickHandler,
		HasValueChangeHandlers<FontPicker> {

	private class FontCell extends HTML {
		String theFont;

		public FontCell(String font) {
			super(font);
			this.theFont = font;
			DOM.setStyleAttribute(getElement(), "backgroundColor", "#D8ECFD");
			DOM.setStyleAttribute(getElement(), "padding", "2px 4px 2px 8px");
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					DOM.setStyleAttribute(getElement(), "backgroundColor",
							"#7FAAFF");
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(MouseOutEvent event) {
					DOM.setStyleAttribute(getElement(), "backgroundColor",
							"#D8ECFD");
				}
			});
		}

		public String getFont() {
			return theFont;
		}

	}

	public enum FontPickerType {
		FONT_FAMILY, FONT_SIZE
	}

	private static final String[] fontFamilies = new String[] {
			"Times New Roman", "Arial", "Courier New", "Georgia", "Trebuchet",
			"Verdana", "Comic Sans MS" };

	private static final String[] fontSizes = new String[] { "xx-small",
			"x-small", "small", "medium", "large", "x-large", "xx-large" };

	ValueChangeHandler<FontPicker> changeHandler = null;

	private String font = "";

	public FontPicker(FontPickerType type) {
		super(true);
		VerticalPanel container = new VerticalPanel();
		DOM.setStyleAttribute(container.getElement(), "border",
				"1px solid  #7FAAFF");
		DOM.setStyleAttribute(container.getElement(), "backgroundColor",
				"#D8ECFD");
		DOM.setStyleAttribute(container.getElement(), "cursor", "pointer");

		String[] fonts = type == FontPickerType.FONT_SIZE ? fontSizes
				: fontFamilies;

		for (int i = 0; i < fonts.length; i++) {
			FontCell cell;
			if (type == FontPickerType.FONT_SIZE) {
				cell = new FontCell("" + (i + 1));
				DOM.setStyleAttribute(cell.getElement(), "fontSize", fonts[i]);
				cell.getElement().getId();
			} else {
				cell = new FontCell(fonts[i]);
				DOM.setStyleAttribute(cell.getElement(), "fontFamily", fonts[i]);
			}
			cell.addClickHandler(this);
			container.add(cell);
		}

		add(container);

		setAnimationEnabled(true);
		setStyleName("hupa-color-picker");
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<FontPicker> handler) {
		assert changeHandler == null : "Change handler is already defined";
		changeHandler = handler;
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				changeHandler = null;
			}
		};
	}

	public String getFontName() {
		return font;
	}

	public String getFontSize() {
		return fontSizes[Integer.valueOf(font).intValue()-1];
		
	}

	@Override
	public void onClick(ClickEvent event) {
		FontCell cell = (FontCell) event.getSource();
		this.font = cell.getFont();
		if (changeHandler != null)
			changeHandler.onValueChange(new ValueChangeEvent<FontPicker>(this) {
			});
	}
}