package com.nurkiewicz.java8;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Uses dirty static methods on purpose, so that it's easier to use during testing.
 */
public class MultiRunner {

	public static final int THREAD_COUNT = 20;

	private static final ExecutorService POOL = Executors.newFixedThreadPool(THREAD_COUNT, threadFactory());

	private static ThreadFactory threadFactory() {
		return new ThreadFactoryBuilder()
				.setDaemon(true)
				.setNameFormat("Multi-%d")
				.build();
	}

	public static void runMultiThreaded(Runnable block) {
		runMultiThreaded(1, block);
	}

	/**
	 * Runs blocks given number of times.
	 * Equivalent to replicating block number of times into Iterable and passing to {@link #runMultiThreaded(Iterable)}
	 *
	 * @param times How many times to execute it
	 * @param block Code to execute
	 */
	public static void runMultiThreaded(int times, Runnable block) {
		throw new UnsupportedOperationException("runMultiThreaded()");
	}

	/**
	 * Runs all given blocks of code, possibly multi-threaded
	 *
	 * @param blocks Blocks of code to execute in a thread pool, each one only once
	 */
	public static void runMultiThreaded(Iterable<Runnable> blocks) {
		throw new UnsupportedOperationException("runMultiThreaded()");
	}

}
