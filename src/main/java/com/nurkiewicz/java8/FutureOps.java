package com.nurkiewicz.java8;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FutureOps {

	private static final ScheduledExecutorService pool = Executors.newScheduledThreadPool(10,
			new ThreadFactoryBuilder()
					.setDaemon(true)
					.setNameFormat("FutureOps-%d")
					.build()
	);

	public static <T> CompletableFuture<T> failed(Throwable t) {
		final CompletableFuture<T> future = new CompletableFuture<T>();
		future.completeExceptionally(t);
		return future;
	}

	public static <T> CompletableFuture<T> never() {
		return new CompletableFuture<>();
	}

	/**
	 * Fails with {@link TimeoutException} after given time
	 */
	public static <T> CompletableFuture<T> timeoutAfter(Duration duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		pool.schedule(
				() -> promise.completeExceptionally(new TimeoutException()),
				duration.toMillis(), TimeUnit.MILLISECONDS);
		return promise;
	}

	/**
	 * Should not block but return {@link CompletableFuture} immediately.
	 */
	public static <T> CompletableFuture<T> toCompletable(Future<T> future) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		pool.submit(() -> promise.complete(future.get()));
		return promise;
	}

	/**
	 * Filters out futures that failed. Preserves order of input, no matter what was the completion order
	 */
	public static <T> CompletableFuture<List<T>> ignoreFailures(List<CompletableFuture<T>> futures) {
		final CompletableFuture<List<T>> promise = new CompletableFuture<>();
		final List<T> results = new CopyOnWriteArrayList<>();
		IntStream.range(0, futures.size()).forEach(i -> results.add(null));
		waitForAllToCompleteOrFail(futures, promise, results);
		return promise;
	}

	private static <T> void waitForAllToCompleteOrFail(List<CompletableFuture<T>> futures, CompletableFuture<List<T>> promise, List<T> results) {
		final AtomicInteger counter = new AtomicInteger(futures.size());
		for (int i = 0; i < futures.size(); i++) {
			final int idx = i;
			futures.get(i).handle((BiFunction<T, Throwable, T>) (result, throwable) -> {
				if (result != null) {
					results.set(idx, result);
				}
				if (counter.decrementAndGet() == 0) {
					promise.complete(filterOutNulls(results));
				}
				return null;
			});
		}
	}

	private static <T> List<T> filterOutNulls(List<T> results) {
		return results
				.stream()
				.filter(x -> x != null)
				.collect(toList());
	}

	/**
	 * Takes a {@link CompletableFuture} and returns compatible future, but that completes with delay.
	 * E.g. if underlying future completes after 7 seconds, and we call this method with 2 seconds duration,
	 * resulting future will complete after 9 seconds.
	 * @return {@link CompletableFuture} which completes after underlying future with given duration
	 */
	public static <T> CompletableFuture<T> delay(CompletableFuture<T> future, Duration duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		future.thenAccept(result -> {
			pool.schedule(() -> promise.complete(result), duration.toMillis(), TimeUnit.MILLISECONDS);
		});
		return promise;
	}

}
