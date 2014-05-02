package com.nurkiewicz.java8;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.hamcrest.core.Is.is;

@Ignore
public class J28_CustomFutureOperatorsTest {

	private final ExecutorService pool = Executors.newFixedThreadPool(10);

	@After
	public void closePool() {
		pool.shutdownNow();
	}

	public void shouldTimeoutIfUnderlyingFutureDoesNotResponse() throws Exception {
		//given
		CompletableFuture<String> never = FutureOps.never();

		//when
		try {
			never.get(100, MILLISECONDS);
			failBecauseExceptionWasNotThrown(TimeoutException.class);
		} catch (TimeoutException e) {
			//then
		}
	}

	/**
	 * If primary future does not complete in given time, handle timeout and return special value.
	 */
	@Test
	public void shouldTimeoutAfterSpecifiedTime() throws Exception {
		//given
		CompletableFuture<String> primary = FutureOps.never();
		CompletableFuture<String> timeout = FutureOps.timeoutAfter(Duration.ofMillis(100));
		CompletableFuture<String> any = null; //...

		//when
		final String fallback = any.get(1, SECONDS);

		//then
		assertThat(fallback).isEqualTo("Fallback");
	}

	@Test
	public void shouldConvertOldFutureToCompletableFuture() throws Exception {
		//given
		final Future<Integer> answer = pool.submit(() -> 42);

		//when
		final CompletableFuture<Integer> completableAnswer = FutureOps.toCompletable(answer);

		//then
		AtomicBoolean condition = new AtomicBoolean();
		completableAnswer.thenRun(() -> condition.set(true));
		await().untilAtomic(condition, is(true));
	}

	@Test
	public void shouldIgnoreFailures() throws Exception {
		//given
		final CompletableFuture<Integer> failed = FutureOps.failed(new UnsupportedOperationException("Don't panic!"));
		final CompletableFuture<Integer> first = completedFuture(42);
		final CompletableFuture<Integer> second = completedFuture(45);
		final CompletableFuture<Integer> broken = FutureOps.failed(new UnsupportedOperationException("Simulated"));

		//when
		final CompletableFuture<List<Integer>> succeeded = FutureOps.ignoreFailures(Arrays.asList(failed, first, second, broken));

		//then
		assertThat(succeeded.get(1, TimeUnit.SECONDS)).containsExactly(42, 45);
	}

	/**
	 * If it takes more than a second for future to complete, ignore it
	 */
	@Test
	public void shouldIgnoreFuturesRunningForTooLong() throws Exception {
		//given
		final CompletableFuture<Integer> later = FutureOps.delay(completedFuture(45), Duration.ofMillis(500));
		final CompletableFuture<Integer> immediately = completedFuture(42);
		final CompletableFuture<Integer> tooLate = FutureOps.delay(completedFuture(17), Duration.ofDays(1));
		final CompletableFuture<Integer> never = FutureOps.never();

		//when
		CompletableFuture<List<Integer>> fastAndSuccess = null;

		//then
		assertThat(fastAndSuccess.get(1, TimeUnit.SECONDS)).containsExactly(42, 45);
	}

}