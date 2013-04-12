package com.freespeedreader.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("visitor")
public interface VisitorService extends RemoteService {
	Long increment() throws IllegalArgumentException;
}
