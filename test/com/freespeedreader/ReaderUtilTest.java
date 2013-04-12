package com.freespeedreader;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.freespeedreader.client.model.Line;
import com.freespeedreader.client.model.Word;
import com.freespeedreader.client.services.ReaderUtil;

public class ReaderUtilTest {

	@Test
	public void readerUtilTest() {
		List<Word> words = new ArrayList<Word>();
		words.add(new Word(0, 0, 5, "puneet"));
		words.add(new Word(7, 0, 12, "puneet"));
		words.add(new Word(14, 0, 19, "puneet"));
		words.add(new Word(21, 0, 26, "puneet"));
		Line line = new Line(words, "puneet", 0, 0, 0);
		System.out.println(ReaderUtil.getNewSteps(line,2));
	}
}
