package com.nurkiewicz.java8.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.nurkiewicz.rxjava.stackoverflow.ArtificialSleepWrapper;
import com.nurkiewicz.rxjava.stackoverflow.HttpStackOverflowClient;
import com.nurkiewicz.rxjava.stackoverflow.InjectErrorsWrapper;
import com.nurkiewicz.rxjava.stackoverflow.LoggingWrapper;
import com.nurkiewicz.rxjava.stackoverflow.StackOverflowClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Ignore
public class AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(AbstractFuturesTest.class);

	protected final ExecutorService executorService = Executors.newFixedThreadPool(10, threadFactory());

	@Rule
	public TestName testName = new TestName();

	private ThreadFactory threadFactory() {
		return new ThreadFactoryBuilder().setNameFormat("WJUG-pool-%d").build();
	}

	protected final StackOverflowClient client = new LoggingWrapper(
			new InjectErrorsWrapper(
					new ArtificialSleepWrapper(
							new HttpStackOverflowClient()
					), "php"
			)
	);

	@Before
	public void logTestStart() {
		log.debug("Starting: {}", testName.getMethodName());
	}

	@After
	public void stopPool() throws InterruptedException {
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}

	protected CompletableFuture<String> questions(String tag) {
		return CompletableFuture.supplyAsync(() ->
				client.mostRecentQuestionAbout(tag),
				executorService);
	}

}
