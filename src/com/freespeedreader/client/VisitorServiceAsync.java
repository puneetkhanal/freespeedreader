package com.freespeedreader.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface VisitorServiceAsync {
	void increment(AsyncCallback<Long> callback)
			throws IllegalArgumentException;
}
