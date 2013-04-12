package com.freespeedreader.client.mouse;

import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.Word;
import com.freespeedreader.client.services.TextRenderer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.Timer;

public class MouseClick implements ClickHandler, DoubleClickHandler {

	private boolean isPaused;

	private TextRenderer textRenderer;

	private Timer timer;

	public MouseClick(TextRenderer textRenderer, Timer timer) {
		this.textRenderer = textRenderer;
		this.timer = timer;
	}

	public String getWord(DoubleClickEvent event) {
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
				return currentWord.getWord();
			}
		}
		return null;
	}

	@Override
	public void onClick(ClickEvent event) {
		if (!isPaused) {
			timer.cancel();
			isPaused = true;

		} else {
			isPaused = false;
			timer.scheduleRepeating(1000);
		}

	}

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
		System.out.println("mouse clicked" + event.getX() + ":" + event.getY());
		System.out.println(getWord(event));
	}

}
