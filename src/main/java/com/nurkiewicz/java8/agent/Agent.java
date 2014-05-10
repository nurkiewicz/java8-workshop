package com.nurkiewicz.java8.agent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * A wrapper around any object that asynchronously serializes all modifications one after another.
 * Agent guarantees that at any given time only one transformation is happening.
 * I.e. if two threads send modification at the same time, they are applied sequentially, in another thread.
 * Value inside agent is replaced by the result of transformation. However you might as well mutate such object and return it.
 *
 * @param <T> Type of underlying value, must be immutable.
 * @see <a href="http://clojure.org/agents">Clojure agents</a>
 */
public interface Agent<T> {

	T get();

	/**
	 * Changes the underlying value of agent asynchronously.
	 * Supplied function will be executed later in another thread.
	 *
	 * @param transformFun Function that will be executed against current value.
	 *                     Its outcome will replace current value.
	 */
	void send(UnaryOperator<T> transformFun);

	CompletableFuture<T> sendAndGet(UnaryOperator<T> transformFun);

	/**
	 * Returns value after all already sent operations were processed.
	 * Result of this method will see changes made by all prior modifications.
	 *
	 * @return Future that completes when all previous tasks complete.
	 */
	CompletableFuture<T> getAsync();

	CompletableFuture<T> completeIf(Predicate<T> predicate);

	static <T> Agent<T> create(T initial, ExecutorService pool) {
		return new AsyncAgent<>(initial, pool);
	}

}
