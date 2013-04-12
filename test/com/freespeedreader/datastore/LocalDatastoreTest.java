package com.freespeedreader.datastore;

import com.freespeedreader.server.services.ShardedCounter;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocalDatastoreTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private void doTest() {

		ShardedCounter counter = new ShardedCounter("visitor");
		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
		System.out.println(counter.getCount());
	}

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testInsert1() {
		doTest();
	}

}
