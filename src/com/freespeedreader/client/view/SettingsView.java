package com.freespeedreader.client.view;

import com.freespeedreader.client.model.Settings;
import com.freespeedreader.client.view.FontPicker.FontPickerType;
import com.google.code.gwteyecandy.Button;
import com.google.code.gwteyecandy.ColorInput;
import com.google.code.gwteyecandy.ToggleButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class SettingsView extends Composite implements
		HasValueChangeHandlers<SettingsView> {

	interface SettingsUiBinder extends UiBinder<Widget, SettingsView> {
	}

	private static SettingsUiBinder uiBinder = GWT
			.create(SettingsUiBinder.class);

	@UiField
	ColorInput backgroundColor;

	@UiField
	ToggleButton canvasSettings;

	ValueChangeHandler<SettingsView> changeHandler = null;

	@UiField
	DeckPanel deckPanel;

	@UiField
	Label fixationInterval;

	@UiField
	ListBox fixations;

	@UiField
	Button fixationsIntervalDecrease;

	@UiField
	Button fixationsIntervalIncrease;

	@UiField
	ToggleButton font;

	@UiField
	ColorInput fontColor;

	private ValueChangeHandler<FontPicker> fontHandler = new ValueChangeHandler<FontPicker>() {
		@Override
		public void onValueChange(ValueChangeEvent<FontPicker> event) {
			FontPicker sender = event.getValue();
			if (sender == fontPicker) {
				settings.setFontName(sender.getFontName());
			}
			sender.hide();
			fireEvent(SettingsEvent.FormattingChanged);
		}

	};

	final FontPicker fontPicker = new FontPicker(FontPickerType.FONT_FAMILY);

	@UiField
	ToggleButton fontSettings;

	@UiField
	ListBox fontSize;

	@UiField
	Button heightDecrease;

	@UiField
	Button heightIncrease;

	@UiField
	ColorInput highlightColor;

	@UiField
	Label highlightLabel;

	@UiField
	Button pasteText;

	private Settings settings;

	private SettingsEvent settingsEvent;

	private String text = "";

	@UiField
	ToggleButton trainingModeSettings;

	@UiField
	Button widthDecrease;

	@UiField
	Button widthIncrease;

	public SettingsView() {
		initWidget(uiBinder.createAndBindUi(this));
		fontPicker.addValueChangeHandler(fontHandler);
		settings = new Settings();
		fontColor.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				settings.setFontColor(event.getValue());
				fireEvent(SettingsEvent.FormattingChanged);
			}
		});

		backgroundColor.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				settings.setBackgroundColor(event.getValue());
				fireEvent(SettingsEvent.FormattingChanged);
			}
		});

		highlightColor.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				settings.setHighlightColor(event.getValue());
				fireEvent(SettingsEvent.FormattingChanged);
			}
		});

		fontSize.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				settings.setFontSize(fontSize.getSelectedIndex() + 1);
				fireEvent(SettingsEvent.FormattingChanged);

			}
		});

		fixations.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				settings.setFixations(fixations.getSelectedIndex() + 1);
				fireEvent(SettingsEvent.FormattingChanged);

			}
		});
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<SettingsView> handler) {
		assert changeHandler == null : "Change handler is already defined";
		changeHandler = handler;
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				changeHandler = null;
			}
		};
	}

	public void fireEvent(SettingsEvent settingsEvent) {
		this.settingsEvent = settingsEvent;
		if (changeHandler != null)
			changeHandler
					.onValueChange(new ValueChangeEvent<SettingsView>(this) {
					});
	}

	public void fireEvent(SettingsEvent settingsEvent, String text) {
		this.text = text;
		fireEvent(settingsEvent);
	}

	public Settings getSettings() {
		return settings;
	}

	public SettingsEvent getSettingsEvent() {
		return settingsEvent;
	}

	public String getText() {
		return text;
	}

	@UiHandler("canvasSettings")
	void onCanvasSettingsClick(ClickEvent e) {
		deckPanel.showWidget(0);
		fontSettings.setValue(false);
		trainingModeSettings.setValue(false);
		if (e == null) {
			canvasSettings.setValue(true);
		}

	}

	@UiHandler("fixationsIntervalDecrease")
	void onFixationIntervalDecrease(ClickEvent e) {
		fireEvent(SettingsEvent.FixationsIntervalDecrease);
	}

	@UiHandler("fixationsIntervalIncrease")
	void onFixationIntervalIncrease(ClickEvent e) {
		fireEvent(SettingsEvent.FixationsIntervalIncrease);
	}

	@UiHandler("fontSettings")
	void onFontSettingsClick(ClickEvent e) {
		deckPanel.showWidget(1);
		canvasSettings.setValue(false);
		trainingModeSettings.setValue(false);
		if (e == null) {
			fontSettings.setValue(true);
		}

	}

	@UiHandler("font")
	public void onFontToggle(ValueChangeEvent<Boolean> event) {
		Widget sender = (Widget) event.getSource();
		fontPicker.setPopupPosition(sender.getAbsoluteLeft(),
				sender.getAbsoluteTop() + 20);
		fontPicker.show();
	}

	@UiHandler("heightDecrease")
	void onHeightDecrease(ClickEvent e) {
		fireEvent(SettingsEvent.HeightDecrease);
	}

	@UiHandler("heightIncrease")
	void onHeightIncrease(ClickEvent e) {
		fireEvent(SettingsEvent.HeightIncrease);
	}

	@UiHandler("pasteText")
	void onPasteText(ClickEvent e) {
		MessagePopup.showConfirmation("Paste Text", "Please copy and paste text on the text area below.",
				new IMessageBoxHandler() {

					@Override
					public void onConfirmed(String text) {
						fireEvent(SettingsEvent.PasteText, text);
					}
				});
	}

	@UiHandler("trainingModeSettings")
	void onTrainModeSettingsClick(ClickEvent e) {
		deckPanel.showWidget(2);
		canvasSettings.setValue(false);
		fontSettings.setValue(false);
		if (e == null) {
			trainingModeSettings.setValue(true);
		}
	}

	@UiHandler("widthDecrease")
	void onWidthDecrease(ClickEvent e) {
		fireEvent(SettingsEvent.WidthDecrease);
	}

	@UiHandler("widthIncrease")
	void onWidthIncrease(ClickEvent e) {
		fireEvent(SettingsEvent.WidthIncrease);
	}

	public void showFixationInterval(int interval) {
		fixationInterval.setText("Fixation Interval(" + interval + "ms):");

	}

	public void updateSettingsView(Settings settings) {
		this.settings = settings;
		fontColor.setValue(settings.getFontColor());
		backgroundColor.setValue(settings.getBackgroundColor());
		highlightColor.setValue(settings.getHighlightColor());
		fixations.setValue(settings.getFixations(), settings.getFixations()
				+ "");
		fixations.setItemSelected(settings.getFixations() - 1, true);
		fontSize.setItemSelected(settings.getFontSize() - 1, true);
	}

}
