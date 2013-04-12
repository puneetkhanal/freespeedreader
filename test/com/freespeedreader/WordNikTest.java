package com.freespeedreader;

import java.util.List;

import net.jeremybrooks.knicker.KnickerException;
import net.jeremybrooks.knicker.WordApi;
import net.jeremybrooks.knicker.dto.Definition;
import net.jeremybrooks.knicker.dto.Related;

import org.junit.Test;

import com.freespeedreader.server.services.DictionaryService;

public class WordNikTest {

	@Test
	public void wordNikTest() throws KnickerException {
		// use your API key here
		System.setProperty("WORDNIK_API_KEY",
				"04a1cd18195c1f47d420c024c310af713129c8a72de6eddef");
		//
		//
		// // check the status of the API key
		// TokenStatus status = AccountApi.apiTokenStatus();
		// if (status.isValid()) {
		// System.out.println("API key is valid.");
		// } else {
		// System.out.println("API key is invalid!");
		// System.exit(1);
		// }

		// get a list of definitions for a word
		List<Definition> def = WordApi.definitions("blasphemy");
		List<Related> related = WordApi.related("blasphemy");
		System.out.println(related);
		System.out.println("Found " + def.size() + " definitions.");

		int i = 1;
		for (Definition d : def) {
			System.out.println((i++) + ") " + d.getPartOfSpeech() + ": "
					+ d.getText());
		}
		System.out.println(DictionaryService.getDictionaryResult("blasphemy")
				.toString());
	}
}
