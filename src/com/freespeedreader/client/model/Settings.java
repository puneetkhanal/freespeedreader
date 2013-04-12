package com.freespeedreader.client.model;

public class Settings {

	private String backgroundColor = "#FFFFFF";
	private int canvasHeight;
	private int canvasWidth;
	private int fixationInterval;
	private int fixations = 2;
	private String fontColor = "#000000";
	private String fontName = "Times New Roman";
	private int fontSize = 20;
	private String highlightColor = "rgb(51, 255, 51)";

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public int getFixationInterval() {
		return fixationInterval;
	}

	public int getFixations() {
		return fixations;
	}

	public String getFontColor() {
		return fontColor;
	}

	public String getFontName() {
		return fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getHighlightColor() {
		return highlightColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public void setFixationInterval(int fixationInterval) {
		this.fixationInterval = fixationInterval;
	}

	public void setFixations(int fixations) {
		this.fixations = fixations;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setHighlightColor(String highlightColor) {
		this.highlightColor = highlightColor;
	}

	@Override
	public String toString() {
		return "Settings [backgroundColor=" + backgroundColor + ", fontColor="
				+ fontColor + ", fontName=" + fontName + ", fontSize="
				+ fontSize + ", highlightColor=" + highlightColor
				+ ", fixations=" + fixations + "]";
	}

}
