package com.freespeedreader.client.model;

public class Word {
	private double endIndex;

	private double startIndex;

	private String word;

	private double y;

	public Word(double startIndex, double y, double endIndex, String word) {
		this.startIndex = startIndex;
		this.y = y;
		this.endIndex = endIndex;
		this.word = word;
	}

	public double getEndIndex() {
		return endIndex;
	}

	public double getStartIndex() {
		return startIndex;
	}

	public String getWord() {
		return word;
	}

	public double getY() {
		return y;
	}

	public void setEndIndex(double endIndex) {
		this.endIndex = endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setStartIndex(double startIndex) {
		this.startIndex = startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Word [startIndex=" + startIndex + ", endIndex=" + endIndex
				+ ", word=" + word + "]";
	}
}
