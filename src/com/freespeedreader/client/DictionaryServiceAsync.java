package com.freespeedreader.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DictionaryServiceAsync {
	void lookupMeaning(String word, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
