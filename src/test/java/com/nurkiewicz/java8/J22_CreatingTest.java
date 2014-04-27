package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.AbstractFuturesTest;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

@Ignore
public class J22_CreatingTest extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(J22_CreatingTest.class);

	@Test
	public void supplyAsync() throws Exception {
		final CompletableFuture<String> java = CompletableFuture.supplyAsync(() ->
				client.mostRecentQuestionAbout("java")
		);
		log.debug("Found: '{}'", java.get());
	}

	@Test
	public void supplyAsyncWithCustomExecutor() throws Exception {
		final CompletableFuture<String> java = CompletableFuture.supplyAsync(() ->
				client.mostRecentQuestionAbout("java"),
				executorService);
		log.debug("Found: '{}'", java.get());
	}

}

