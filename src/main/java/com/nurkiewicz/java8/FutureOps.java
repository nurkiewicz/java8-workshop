package com.nurkiewicz.java8;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class FutureOps {

	public static <T> CompletableFuture<T> failed(Throwable t) {
		final CompletableFuture<T> future = new CompletableFuture<T>();
		future.completeExceptionally(t);
		return future;
	}

	public static <T> CompletableFuture<T> never() {
		throw new UnsupportedOperationException("never()");
	}

	/**
	 * Fails with {@link TimeoutException} after given time
	 */
	public static <T> CompletableFuture<T> timeoutAfter(Duration duration) {
		throw new UnsupportedOperationException("timeoutAfter()");
	}

	/**
	 * Should not block but return {@link CompletableFuture} immediately.
	 */
	public static <T> CompletableFuture<T> toCompletable(Future<T> future) {
		throw new UnsupportedOperationException("toCompletable()");
	}

	/**
	 * Filters out futures that failed. Preserves order
	 */
	public static <T> CompletableFuture<List<T>> ignoreFailures(Collection<CompletableFuture<T>> futures) {
		throw new UnsupportedOperationException("ignoreFailures()");
	}

	/**
	 * @return {@link CompletableFuture} which completes after underlying future with given duration
	 */
	public static <T> CompletableFuture<T> delay(CompletableFuture<T> future, Duration duration) {
		throw new UnsupportedOperationException("delay()");
	}

}
