package com.freespeedreader.client.services;

import java.util.ArrayList;
import java.util.List;

import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.Step;

public class Page {

	private int endIndex;
	private int lineNumber=0;
	private List<Line> lines = new ArrayList<Line>();
	private int noOfWordsInPage;
	private int startIndex = 0;
	private int stepIndex;
	private int stepNumber=0;
	private List<Step> steps = new ArrayList<Step>();

	public int getEndIndex() {
		return endIndex;
	}

	public int getLineNumber(){
		return lineNumber;
	}
	public List<Line> getLines() {
		return lines;
	}

	public int getNoOfWordsInPage() {
		return noOfWordsInPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public Step getStep(int stops) {

		Step step=lines.get(lineNumber).getStep(stops);
		if(lines.get(lineNumber).getStepIndex()>=lines.get(lineNumber).getSteps().size()){
			lines.get(lineNumber).setSteps(null);
			lineNumber++;
			if(lineNumber>=lines.size()){
				lineNumber=0;
			}
		}
		return step;
	}

	public int getStepIndex() {
		return stepIndex;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public void setNoOfWordsInPage(int noOfWordsInPage) {
		this.noOfWordsInPage = noOfWordsInPage;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setStepIndex(int stepIndex) {
		this.stepIndex = stepIndex;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

}
