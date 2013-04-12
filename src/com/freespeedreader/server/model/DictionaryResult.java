package com.freespeedreader.server.model;

import java.util.List;

import net.jeremybrooks.knicker.dto.Definition;
import net.jeremybrooks.knicker.dto.Related;

public class DictionaryResult {

	private List<Definition> definitions;

	private List<Related> relations;

	private String word;

	public DictionaryResult(String word, List<Definition> definitions) {
		this.definitions = definitions;
		this.word = word;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public List<Related> getRelations() {
		return relations;
	}

	public String getWord() {
		return word;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	public void setRelations(List<Related> relations) {
		this.relations = relations;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		StringBuilder definitionText = new StringBuilder("<html>" + word
				+ "<br></br>");
		int i = 1;
		for (Definition definition : definitions) {
			definitionText.append((i++) + ") " + definition.getPartOfSpeech()
					+ ": " + definition.getText() + "<br></br>");
		}
		definitionText.append("</html>");
		return definitionText.toString();
	}

}
