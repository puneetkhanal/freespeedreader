package com.freespeedreader.client.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.RgbaColor;
import com.freespeedreader.client.model.Settings;
import com.freespeedreader.client.model.Step;
import com.freespeedreader.client.model.Word;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.TextMetrics;

public class TextRendererBak {

	private Page currentPage;
	
	private int numberOfWords;
	int pageIndex;

	List<Page> pages = new ArrayList<Page>();

	private int startIndex;

	private List<String> wordsArrayList;

	public TextRendererBak(String text) {
		this.wordsArrayList = new ArrayList<String>(Arrays.asList(text.trim()
				.split(" ")));
	}

	public Page createPage(Context2d context, double x, double y,
			double maxWidth, double canvasWidth, double canvasHeight,
			boolean trainingMode, boolean isPaused, Settings settings) {
		Page page = new Page();
		List<Line> lines = new ArrayList<Line>();
		CssColor color = CssColor.make(settings.getFontColor());
		context.setFillStyle(color);
		context.setFont(settings.getFontSize() + "px " + settings.getFontName());
		int noOfWordsInAPage = 0;
		if (startIndex != 0) {
			startIndex = startIndex + 1;
		}
		if (startIndex >= wordsArrayList.size()) {
			return null;
		}
		page.setStartIndex(startIndex);
		StringBuilder line = new StringBuilder();
		List<Word> wordsList = new ArrayList<Word>();
		int lineNumber = 0;
		double horizontalDistance = x;
		for (; startIndex < wordsArrayList.size(); startIndex++) {
			String word = wordsArrayList.get(startIndex);
			StringBuilder testLine = new StringBuilder(line + word + " ");
			TextMetrics metrics = context.measureText(testLine.toString());
			double endIndex = horizontalDistance
					+ context.measureText(word + " ").getWidth();
			double lineWidth = metrics.getWidth();
			if (lineWidth > (maxWidth - 20)) {
				if (testLine.toString().split(" ").length == 1) {
					int i = 1;
					String slicedWord = "";
					while (true) {
						slicedWord = word.substring(0, (word.length() - i));
						metrics = context.measureText(slicedWord);
						if (metrics.getWidth() < (maxWidth - 20)) {
							break;
						}
						i++;
					}
					wordsArrayList.set(startIndex, slicedWord);
					if ((startIndex + 1) >= wordsArrayList.size()) {
						wordsArrayList.add(word.substring(word.length() - i,
								word.length()));
					} else {
						wordsArrayList
								.set(startIndex + 1,
										word.substring(word.length() - i,
												word.length())
												+ " "
												+ wordsArrayList
														.get(startIndex + 1));
					}
					word = wordsArrayList.get(startIndex);
					testLine = new StringBuilder(line + word + " ");
					metrics = context.measureText(testLine.toString());
					endIndex = horizontalDistance
							+ context.measureText(word + " ").getWidth();
					wordsList.add(new Word(horizontalDistance, y, endIndex,
							word));
					Line lineObj = new Line(wordsList, testLine.toString(),
							lineNumber, x, y);
					lines.add(lineObj);
					line = new StringBuilder();
					wordsList = new ArrayList<Word>();
					horizontalDistance = x;
					y += settings.getFontSize() + 5;
					if (y >= canvasHeight) {
						break;
					}
				} else {
					startIndex = startIndex - 1;
					lineNumber++;
					Line lineObj = new Line(wordsList, line.toString(),
							lineNumber, x, y);

					lines.add(lineObj);

					line = new StringBuilder();
					wordsList = new ArrayList<Word>();
					horizontalDistance = x;
					y += settings.getFontSize() + 5;
					if (y >= canvasHeight) {
						break;
					}
				}
			} else {
				noOfWordsInAPage++;
				wordsList.add(new Word(horizontalDistance, y, endIndex, word));
				horizontalDistance = endIndex;
				line = testLine;
			}
		}
		if (!wordsList.isEmpty()) {
			lineNumber++;
			Line lineObj = new Line(wordsList, line.toString(), lineNumber, x,
					y);
			lines.add(lineObj);
		}

		page.setLines(lines);
		
		page.setNoOfWordsInPage(noOfWordsInAPage);
		page.setEndIndex(startIndex);
		System.out.println(lines);

		pages.add(page);
		return page;
	}

	public boolean fillLines(Context2d context, double canvasWidth,
			double canvasHeight, Settings settings) {
		CssColor color = CssColor.make(settings.getFontColor());
		context.setFillStyle(color);
		context.setFont(settings.getFontSize() + "px " + settings.getFontName());
		for (Line line : currentPage.getLines()) {
			context.fillText(line.getLine(), line.getX(), line.getY());
		}
		return true;
	}

	public boolean fillLinesAndRect(Context2d context, double canvasWidth,
			double canvasHeight, boolean isPaused, Settings settings) {

		RgbaColor rgbaColor = RgbaColor.fromRgb(settings.getHighlightColor());
		CssColor randomColor = CssColor.make("rgba(" + rgbaColor.r() + ", "
				+ rgbaColor.g() + "," + rgbaColor.b() + ", " + 0.5 + ")");
		Step step = null;
		step = currentPage.getStep(settings.getFixations());
		context.setFillStyle(randomColor);
		context.fillRect(step.getStartIndex(),
				step.getY() - settings.getFontSize() + 5, step.getEndIndex()
						- step.getStartIndex(), settings.getFontSize());
		fillLines(context, canvasWidth, canvasHeight, settings);

		if (!isPaused) {
			currentPage.setStepIndex(currentPage.getStepIndex() + 1);
			numberOfWords += step.getNoOfWords();
			System.out.println(step);
		}
		if (currentPage.getLineNumber() >= currentPage.getLines().size()) {
			pageIndex++;
			if (pageIndex >= pages.size()) {
				pageIndex = pageIndex - 1;
				return false;
			}
		}
		return true;

	}

	public List<Line> getLines() {
		return currentPage.getLines();
	}

	public int getNumberOfWords() {
		return numberOfWords;

	}

	public String getPageNumberText() {
		return (pageIndex + 1) + " of " + pages.size();
	}

	public int getTotalNumberOfWords() {
		return currentPage.getEndIndex();
	}

	public void highlightWord(Context2d context, Word word, Settings settings) {
		if (word != null) {
			RgbaColor rgbaColor = RgbaColor.fromHex("#0000A0");
			CssColor randomColor = CssColor.make("rgba(" + rgbaColor.r() + ", "
					+ rgbaColor.g() + "," + rgbaColor.b() + ", " + 0.5 + ")");
			context.setFillStyle(randomColor);
			context.fillRect(word.getStartIndex(),
					word.getY() - settings.getFontSize() + 5,
					word.getEndIndex() - word.getStartIndex(),
					settings.getFontSize());
		}
	}

	public boolean nextPage() {
		if (pageIndex == pages.size() - 1) {
			return false;
		} else {
			pages.get(pageIndex).setStepIndex(0);
			pageIndex = pageIndex + 1;
			pages.get(pageIndex).setStepIndex(0);
			return true;
		}
	}

	public boolean previousPage() {
		if (pageIndex == 0) {
			return false;
		} else {
			pages.get(pageIndex).setStepIndex(0);
			pageIndex = pageIndex - 1;
			pages.get(pageIndex).setStepIndex(0);
			return true;
		}
	}

	public void reset() {
		pageIndex = 0;
	}

	public void resetNumberOfwords() {
		numberOfWords = 0;
	}

	public void updateContext(Context2d context, Settings settings) {
		startIndex = 0;
		currentPage = null;
		pageIndex = 0;
		pages = new ArrayList<Page>();
		numberOfWords=0;
		CssColor color = CssColor.make(settings.getFontColor());
		context.setFillStyle(color);
		context.setFont(settings.getFontSize() + "px " + settings.getFontName());
	}

	public boolean wrapText(Context2d context, double x, double y,
			double maxWidth, double canvasWidth, double canvasHeight,
			boolean trainingMode, boolean isPaused, Settings settings) {
		if (currentPage == null) {
			currentPage = createPage(context, x, y, maxWidth, canvasWidth,
					canvasHeight, trainingMode, isPaused, settings);
		} else {
			currentPage = pages.get(pageIndex);
		}
		if (trainingMode) {
			return fillLinesAndRect(context, canvasWidth, canvasHeight,
					isPaused, settings);
		} else {
			return fillLines(context, canvasWidth, canvasHeight, settings);
		}

	}

}
