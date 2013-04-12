package com.freespeedreader.client.model;

import java.util.List;

import com.freespeedreader.client.services.ReaderUtil;

public class Line {

	private String line;

	private int lineNumber;

	private Step nextStep;

	private int stepIndex = 0;

	private List<Step> steps;

	private List<Word> words;

	private double x;

	private double y;

	public Line(List<Word> words, String line, int lineNumber, double x,
			double y) {
		super();
		this.words = words;
		this.line = line;
		this.lineNumber = lineNumber;
		this.x = x;
		this.y = y;
	}

	public String getLine() {
		return line;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public Step getNextStep() {
		if (this.nextStep == null) {
			throw new RuntimeException("null next step");
		}
		return this.nextStep;
	}

	public Step getStep(int stops) {
		if (this.steps == null) {
			steps = ReaderUtil.getSteps(this, stops);
		}
		return steps.get(stepIndex++);
	}

	public int getStepIndex() {
		return stepIndex;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public List<Word> getWords() {
		return words;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void goToNextStep() {
		if (this.nextStep == null) {
			this.nextStep = steps.get(0);
			stepIndex++;
		} else {
			if (stepIndex >= steps.size()) {
				stepIndex = 0;
				this.nextStep = null;
			} else {
				this.nextStep = steps.get(stepIndex);
				stepIndex++;
			}
		}
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Line [line=" + line + ", lineNumber=" + lineNumber
				+ ", nextStep=" + nextStep + ", stepIndex=" + stepIndex
				+ ", steps=" + steps + ", words=" + words + ", x=" + x + ", y="
				+ y + "]";
	}

}
