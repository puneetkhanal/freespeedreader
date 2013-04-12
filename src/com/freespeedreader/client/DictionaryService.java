package com.freespeedreader.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dictionary")
public interface DictionaryService extends RemoteService {
	String lookupMeaning(String word) throws IllegalArgumentException;
}
