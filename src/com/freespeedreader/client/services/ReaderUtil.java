package com.freespeedreader.client.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.Step;
import com.freespeedreader.client.model.Word;

public class ReaderUtil {

	public static List<Step> getNewSteps(Line line, int lineStops) {
		List<Step> steps = new ArrayList<Step>();
		List<Word> words = line.getWords();
		if (words.size() == 1) {
			steps.add(new Step(words.get(0).getStartIndex(), words.get(0)
					.getEndIndex(), line.getY(), 1));
			return steps;
		}
		double startIndex = words.get(0).getStartIndex();
		double endIndex = words.get(words.size() - 1).getEndIndex();
		double groupingPoint = (startIndex + endIndex) / lineStops;
		double groupingStartIndex = 0;
		int wordCounter = 0;
		boolean terminate = false;
		groupingStartIndex = words.get(0).getStartIndex();
		for (int i = 0; i < words.size(); i++) {
			Word word = words.get(i);
			wordCounter++;

			if ((word.getEndIndex() - groupingStartIndex) >= (groupingPoint - 1)
					|| i == words.size() - 1) {
				System.out.println((word.getStartIndex() - groupingPoint) + ":"
						+ (word.getEndIndex() - groupingPoint));
				double fromStartIndex = (word.getStartIndex() - groupingPoint)
						* -1;
				double endStartIndex = (word.getEndIndex() - groupingPoint);
				if (fromStartIndex > endStartIndex||terminate) {
					steps.add(new Step(groupingStartIndex, word.getEndIndex(),
							line.getY(), wordCounter));
				} else {
					Word prevWord = words.get(i - 1);
					steps.add(new Step(groupingStartIndex, prevWord
							.getEndIndex(), line.getY(), wordCounter - 1));

					if (terminate) {
						break;
					}
					
					if (i == words.size() - 1) {
						terminate = true;
					}
					i = i - 1;
				}
				wordCounter = 0;

				if (i != words.size() - 1) {
					groupingStartIndex = words.get(i + 1).getStartIndex();
				}
			}
		}
		return steps;
	}

	public static List<Step> getSteps(Line line, int lineStops) {
		List<Step> steps = new ArrayList<Step>();
		List<Word> words = line.getWords();
		int noOfWord = line.getWords().size();
		double doubleGroup = noOfWord * 1.0 / lineStops;
		int group = 0;
		if (doubleGroup < lineStops) {
			BigDecimal value = new BigDecimal(doubleGroup);
			BigDecimal scaled = value.setScale(0, RoundingMode.HALF_UP);
			group = scaled.toBigInteger().intValue();
		} else {
			group = noOfWord / lineStops;
		}
		if (lineStops == 1) {
			steps.add(new Step(words.get(0).getStartIndex(), words.get(
					noOfWord - 1).getEndIndex(), line.getY(), noOfWord));
			return steps;
		}

		double startIndex = words.get(0).getStartIndex();
		int noOfWordsInPrevStep = 0;
		for (int i = 0; i < lineStops; i++) {
			int noOfWordInCurrentStep = 0;
			if (i == lineStops - 1) {
				noOfWordInCurrentStep = noOfWord - noOfWordsInPrevStep;
				if (noOfWordInCurrentStep > 0) {
					steps.add(new Step(startIndex, words.get(noOfWord - 1)
							.getEndIndex(), line.getY(), noOfWordInCurrentStep));
				}
			} else if ((i + 1) * group < noOfWord) {
				noOfWordInCurrentStep = ((i + 1) * group) - noOfWordsInPrevStep;
				if (noOfWordInCurrentStep > 0) {
					steps.add(new Step(startIndex, words.get(
							((i + 1) * group) - 1).getEndIndex(), line.getY(),
							noOfWordInCurrentStep));
					startIndex = words.get(((i + 1) * group) - 1).getEndIndex();
					noOfWordsInPrevStep += noOfWordInCurrentStep;
				}
			}
		}
		return steps;
	}

}
