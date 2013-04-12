package com.freespeedreader.server.services;

import net.jeremybrooks.knicker.WordApi;

import com.freespeedreader.client.DictionaryService;
import com.freespeedreader.server.model.DictionaryResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DictionaryServiceImpl extends RemoteServiceServlet implements
		DictionaryService {

	@Override
	public String lookupMeaning(String word) throws IllegalArgumentException {
		System.setProperty("WORDNIK_API_KEY",
				"04a1cd18195c1f47d420c024c310af713129c8a72de6eddef");
		word = word.replaceAll("\\W", "");
		try {
			return new DictionaryResult(word, WordApi.definitions(word
					.toLowerCase())).toString();
		} catch (Exception e) {
			return null;
		}
	}

}
