package com.nurkiewicz.java8.agent;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class AsyncAgent<T> implements Agent<T> {

	private volatile T value;
	private final ExecutorService pool;
	private final Queue<UnaryOperator<T>> pendingOperations = new LinkedBlockingQueue<>();
	private final Map<CompletableFuture<T>, Predicate<T>> pendingPromises = new ConcurrentHashMap<>();
	private final Lock executionLock = new ReentrantLock();

	public AsyncAgent(T initial, ExecutorService pool) {
		this.value = initial;
		this.pool = pool;
	}

	@Override
	public T get() {
		return locked(() -> value);
	}

	@Override
	public void send(UnaryOperator<T> transformFun) {
		pendingOperations.add(transformFun);
		pool.submit(this::tryExecutingPendingOperations);
	}

	private void tryExecutingPendingOperations() {
		if (!pendingOperations.isEmpty()) {
			locked(this::executePendingOperations);
		}
	}

	private void executePendingOperations() {
		while (!pendingOperations.isEmpty()) {
			value = pendingOperations.poll().apply(value);
			completeMetPromises();
		}
	}

	@Override
	public CompletableFuture<T> sendAndGet(UnaryOperator<T> transformFun) {
		final CompletableFuture<T> promise = new CompletableFuture<T>();
		send(prev -> {
			final T newValue = transformFun.apply(prev);
			promise.complete(newValue);
			return newValue;
		});
		return promise;
	}

	@Override
	public CompletableFuture<T> getAsync() {
		final CompletableFuture<T> promise = new CompletableFuture<T>();
		send(cur -> {
			promise.complete(cur);
			return cur;
		});
		return promise;
	}

	@Override
	public CompletableFuture<T> completeIf(Predicate<T> predicate) {
		final CompletableFuture<T> promise = new CompletableFuture<T>();
		pendingPromises.put(promise, predicate);
		completeMetPromises();
		return promise;
	}

	private void completeMetPromises() {
		locked(() ->
				pendingPromises.forEach((promise, predicate) -> {
					if (predicate.test(value)) {
						promise.complete(value);
						pendingPromises.remove(promise);
					}
				}));
	}

	private void locked(Runnable block) {
		executionLock.lock();
		try {
			block.run();
		} finally {
			executionLock.unlock();
		}
	}

	private T locked(Supplier<T> block) {
		executionLock.lock();
		try {
			return block.get();
		} finally {
			executionLock.unlock();
		}
	}
}
