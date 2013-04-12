package com.freespeedreader.server.services;

import com.freespeedreader.client.VisitorService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class VisitorServiceImpl extends RemoteServiceServlet implements
		VisitorService {

	private ShardedCounter shardCounter = new ShardedCounter("visitor");

	@Override
	public Long increment() throws IllegalArgumentException {
		shardCounter.increment();
		return new Long(shardCounter.getCount());
	}

}
