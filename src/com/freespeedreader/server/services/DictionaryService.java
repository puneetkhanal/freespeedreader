package com.freespeedreader.server.services;

import com.freespeedreader.server.model.DictionaryResult;

import net.jeremybrooks.knicker.WordApi;

public class DictionaryService {

	public static DictionaryResult getDictionaryResult(String word) {
		System.setProperty("WORDNIK_API_KEY",
				"04a1cd18195c1f47d420c024c310af713129c8a72de6eddef");
		word = word.replace(",", "");
		try {
			return new DictionaryResult(word, WordApi.definitions(word
					.toLowerCase()));
		} catch (Exception e) {
			return null;
		}
	}
}
