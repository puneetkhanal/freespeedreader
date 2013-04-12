package com.freespeedreader.client.view;

import java.util.LinkedList;
import java.util.Queue;

import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.Settings;
import com.freespeedreader.client.model.Word;
import com.freespeedreader.client.services.TextRenderer;
import com.freespeedreader.client.utils.DateUtil;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements IMainView {

	private class IncreasingTimer extends Timer {

		@Override
		public void run() {
			totalmsecs = totalmsecs + 1000;
			showTime(totalmsecs);
			if (trainingMode.getValue()) {
				showSpeed();
			}
		}

	}

	private class IntervalTimer extends Timer {

		@Override
		public void run() {
			if (textRenderer.isFinished()) {
				this.cancel();
			} else {
				draw(false);
			}
		}
	}

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);

	@UiField(provided = true)
	Canvas canvas;

	private int canvasHeight = 300;
	private int canvasWidth = 600;
	private Context2d context;

	private IncreasingTimer increasingTimer = new IncreasingTimer();
	private int interval = 1000;
	private IntervalTimer intervalTimer = new IntervalTimer();
	@UiField
	PushButton nextPage;
	private int numberOfWordsPerMinute = 0;
	Queue<Integer> numberOfwordsPerSecondQueue = new LinkedList<Integer>();

	@UiField
	PushButton pause;

	private Presenter presenter;

	@UiField
	PushButton previousPage;

	@UiField
	Label remainingWordsLabel;

	@UiField
	SettingsView settingsView;

	@UiField
	Label speedLabel;

	@UiField
	RadioButton speedTestMode;

	@UiField
	Label speedTestResultLabel;
	@UiField
	PushButton start;

	@UiField
	PushButton stop;

	private TextRenderer textRenderer = new TextRenderer(
			"Welcome to free speed reader application. Here, you can calculate your current reading speed as well as train to increase your reading speed. Please click on \"Paste Text\" from menu above to paste the text and get started. Click on the word to lookup its meaning in popup or vocabulary.com.");

	@UiField
	Label timeLabel;

	private int totalmsecs = 0;

	@UiField
	RadioButton trainingMode;

	@UiField
	RadioButton vocabulary;

	@UiField
	RadioButton wordnik;

	public MainView() {
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			RootPanel
					.get()
					.add(new Label(
							"Sorry, your browser doesn't support the HTML5 Canvas element"));
			return;
		}
		initWidget(uiBinder.createAndBindUi(this));
		speedTestMode.setValue(true);
		speedTestResultLabel.setVisible(false);
		pause.setVisible(false);
		speedLabel.setVisible(false);
		stop.setVisible(false);
		canvas.setWidth(canvasWidth + "px");
		canvas.setCoordinateSpaceWidth(canvasWidth);
		canvas.setHeight(canvasHeight + "px");
		canvas.setCoordinateSpaceHeight(canvasHeight);
		context = canvas.getContext2d();

		canvas.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onPauseClick(null);
				draw(false);
				Word selectedWord = getWord(event);
				textRenderer.highlightWord(context, selectedWord,
						settingsView.getSettings());
				if (vocabulary.getValue() || event.isControlKeyDown()) {
					openWordMeaningInNewTab(selectedWord.getWord());
				} else {
					presenter.lookupMeaning(selectedWord.getWord());
				}

			}
		});

		settingsView
				.addValueChangeHandler(new ValueChangeHandler<SettingsView>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<SettingsView> event) {
						switch (settingsView.getSettingsEvent()) {
						case WidthIncrease:
							onWidthIncrease();
							break;
						case WidthDecrease:
							onWidthDecrease();
							break;
						case HeightIncrease:
							onHeightIncrease();
							break;
						case HeightDecrease:
							onHeightDecrease();
							break;
						case FixationsIntervalIncrease:
							onFixationsIntervalIncrease();
							break;
						case FixationsIntervalDecrease:
							onFixationsIntervalDecrease();
							break;
						case PasteText:
							onPasteText(settingsView.getText());
							break;
						default:
							textRenderer.updateContext(context,
									settingsView.getSettings());
							intervalTimer.cancel();
							draw(true);
							if (!start.isVisible()) {
								intervalTimer.scheduleRepeating(1000);
								break;
							}

						}

					}
				});

		settingsView.updateSettingsView(new Settings());

		draw(true);
		showSpeed();
		showTime(totalmsecs);
		showRemainingWords();
		settingsView.showFixationInterval(interval);
		settingsView.onCanvasSettingsClick(null);
	}

	public void draw(boolean pagingReferenceChanged) {
		final double maxWidth = canvasWidth - 20;
		final double x = 20;
		final double y = 30;
		canvas.getElement()
				.getStyle()
				.setBackgroundColor(
						settingsView.getSettings().getBackgroundColor());
		canvas.setWidth(canvasWidth + "px");
		canvas.setHeight(canvasHeight + "px");
		System.out.println("canvasWidth;" + canvasWidth);
		canvas.setCoordinateSpaceWidth(canvasWidth);
		canvas.setCoordinateSpaceHeight(canvasHeight);
		if (textRenderer != null) {
			if (!textRenderer.wrapText(context, x, y, maxWidth, canvasWidth,
					canvasHeight, trainingMode.getValue(), start.isVisible(),
					settingsView.getSettings())) {
				if (!textRenderer.isFinished()) {
					intervalTimer.cancel();
					draw(true);
					intervalTimer.scheduleRepeating(interval);
				}
			}
		}
		showRemainingWords();
		context.fill();

	}

	public Word getWord(ClickEvent event) {
		Line currentLine = null;
		for (Line line : textRenderer.getLines()) {
			currentLine = line;
			if (line.getY() > event.getY()) {
				break;
			}
		}
		if (currentLine != null) {
			Word currentWord = null;
			for (Word word : currentLine.getWords()) {

				if (word.getStartIndex() > event.getX()) {
					break;
				}
				currentWord = word;
			}
			if (currentWord != null) {
				return currentWord;
			}
		}
		return null;
	}

	void onFixationsIntervalDecrease() {
		interval = interval - 50;
		if (interval <= 0) {
			interval = 1;
		}
		if (pause.isVisible()) {
			intervalTimer.cancel();
			intervalTimer.scheduleRepeating(interval);
		}
		settingsView.showFixationInterval(interval);
	}

	void onFixationsIntervalIncrease() {
		interval = interval + 50;
		if (pause.isVisible()) {
			intervalTimer.cancel();
			intervalTimer.scheduleRepeating(interval);
		}
		settingsView.showFixationInterval(interval);
	}

	void onHeightDecrease() {
		intervalTimer.cancel();
		canvasHeight -= 10;
		textRenderer.updateContext(context, settingsView.getSettings());
		draw(true);
		showRemainingWords();
		if (!start.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
		}
	}

	void onHeightIncrease() {
		intervalTimer.cancel();
		canvasHeight += settingsView.getSettings().getFontSize() + 5;
		textRenderer.updateContext(context, settingsView.getSettings());
		draw(true);
		showRemainingWords();
		if (!start.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
		}
	}

	@UiHandler("nextPage")
	void onNexPageClick(ClickEvent e) {
		intervalTimer.cancel();
		if (textRenderer.nextPage()) {
			draw(true);
		} else {
			Window.alert("There is no next page.");
		}
		if (pause.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
			increasingTimer.cancel();
			increasingTimer.scheduleRepeating(1000);
		}
		showRemainingWords();
	}

	public void onPasteText(String text) {
		textRenderer = new TextRenderer(text);
		pause.setVisible(false);
		start.setVisible(true);
		totalmsecs = 0;
		showTime(totalmsecs);
		showSpeed();
		numberOfwordsPerSecondQueue = new LinkedList<Integer>();
		numberOfWordsPerMinute = 0;
		increasingTimer.cancel();
		intervalTimer.cancel();
		draw(true);
		showRemainingWords();

	}

	@UiHandler("pause")
	void onPauseClick(ClickEvent e) {
		intervalTimer.cancel();
		increasingTimer.cancel();
		start.setVisible(true);
		pause.setVisible(false);
	}

	@UiHandler("previousPage")
	void onPreviousPageClick(ClickEvent e) {
		intervalTimer.cancel();
		if (textRenderer.previousPage()) {
			draw(true);
		} else {
			Window.alert("There is no previous page.");
		}
		if (pause.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
			increasingTimer.cancel();
			increasingTimer.scheduleRepeating(1000);
		}
		showRemainingWords();
	}

	@UiHandler("speedTestMode")
	void onSpeedTestModeClick(ClickEvent e) {
		speedLabel.setVisible(false);
		totalmsecs = 0;
		showTime(totalmsecs);
		showSpeed();
		numberOfwordsPerSecondQueue = new LinkedList<Integer>();
		numberOfWordsPerMinute = 0;
		increasingTimer.cancel();
		intervalTimer.cancel();
		settingsView.onCanvasSettingsClick(null);
		textRenderer.reset();
		if (pause.isVisible()) {
			start.setVisible(true);
			pause.setVisible(false);
		}
		draw(true);
		stop.setVisible(false);
	}

	@UiHandler("start")
	void onStartClick(ClickEvent e) {
		if (trainingMode.getValue()) {
			intervalTimer.scheduleRepeating(interval);
		}
		increasingTimer.scheduleRepeating(1000);
		start.setVisible(false);
		pause.setVisible(true);
		speedTestResultLabel.setVisible(false);
		stop.setVisible(true);
	}

	@UiHandler("stop")
	void onStop(ClickEvent e) {
		intervalTimer.cancel();
		increasingTimer.cancel();
		if (!speedTestResultLabel.isVisible() && speedTestMode.getValue()) {
			speedTestResultLabel.setVisible(true);
			showSpeedTestResult();
		}

		textRenderer.reset();
		start.setVisible(true);
		pause.setVisible(false);
		stop.setVisible(false);
		totalmsecs = 0;
		numberOfwordsPerSecondQueue = new LinkedList<Integer>();
		numberOfWordsPerMinute = 0;
		showTime(0);
		showSpeed();
		draw(true);
	}

	@UiHandler("trainingMode")
	void onTrainingModeClick(ClickEvent e) {
		speedLabel.setVisible(true);
		speedTestResultLabel.setVisible(false);
		totalmsecs = 0;
		showTime(totalmsecs);
		showSpeed();
		numberOfwordsPerSecondQueue = new LinkedList<Integer>();
		numberOfWordsPerMinute = 0;
		increasingTimer.cancel();
		intervalTimer.cancel();
		textRenderer.reset();
		settingsView.onTrainModeSettingsClick(null);
		draw(true);
		if (pause.isVisible()) {
			start.setVisible(true);
			pause.setVisible(false);
		}
		stop.setVisible(false);
	}

	void onWidthDecrease() {
		intervalTimer.cancel();
		canvasWidth -= settingsView.getSettings().getFontSize() + 5;
		textRenderer.updateContext(context, settingsView.getSettings());
		draw(true);
		showRemainingWords();
		if (!start.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
		}
	}

	void onWidthIncrease() {
		intervalTimer.cancel();
		canvasWidth += 10;
		textRenderer.updateContext(context, settingsView.getSettings());
		draw(true);
		showRemainingWords();
		if (!start.isVisible()) {
			intervalTimer.scheduleRepeating(interval);
		}
	}

	private void openWordMeaningInNewTab(String word) {
		if (word != null) {
			word = word.replaceAll("\\W", "");
			Window.open("http://www.vocabulary.com/dictionary/" + word,
					"_blank", "");
		}
	}

	private void processKeyEvent(NativeEvent event) {
		if (event.getKeyCode() == 37) {
			onPreviousPageClick(null);
		} else if (event.getKeyCode() == 39) {
			onNexPageClick(null);
		} else if (event.getKeyCode() == 38) {
			// onFixationsIntervalDecrease(null);
		} else if (event.getKeyCode() == 40) {
			// onFixationsIntervalDecrease(null);
		} else if (event.getKeyCode() == 32) {
			if (start.isVisible()) {
				onStartClick(null);
			} else {
				onPauseClick(null);
			}
		} else if (event.getKeyCode() == 188) {
			// onWidthDecrease(null);
		} else if (event.getKeyCode() == 190) {
			// onWidthIncrease(null);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void showRemainingWords() {
		remainingWordsLabel.setText("Remaining Words: "
				+ textRenderer.getRemainingWords(settingsView.getSettings()));
	}

	private void showSpeed() {
		if (totalmsecs == 0) {
			speedLabel.setText("WPM" + " : " + 0);
		} else {
			int numberOfWordsPerSecond = textRenderer.getNumberOfWords();
			textRenderer.resetNumberOfwords();
			numberOfwordsPerSecondQueue.add(numberOfWordsPerSecond);
			numberOfWordsPerMinute += numberOfWordsPerSecond;
			if ((totalmsecs / 1000) <= 60) {
				speedLabel.setText("WPM" + " : "
						+ (numberOfWordsPerMinute / (totalmsecs / 1000)) * 60);
			} else {
				numberOfWordsPerMinute -= numberOfwordsPerSecondQueue.remove();
				if (numberOfwordsPerSecondQueue.size() > 60) {
					System.out.println("warning");
				}
				speedLabel.setText("WPM" + " : " + numberOfWordsPerMinute);
			}
		}
	}

	private void showSpeedTestResult() {
		double doubleValue = (textRenderer.getTotalNumberOfWords() + 0.0)
				/ (totalmsecs / 1000);
		int speed = (int) (doubleValue * 60);
		speedTestResultLabel.setText("Your reading speed was " + speed
				+ " Words Per Minute(WPM).");
	}

	private void showTime(int secs) {
		timeLabel.setText(DateUtil.getDuration(secs));
	}
}
