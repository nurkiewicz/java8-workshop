package com.nurkiewicz.java8.agent;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class AsyncAgent<T> implements Agent<T> {

	public AsyncAgent() {
	}

	public AsyncAgent(T initial) {
	}

	@Override
	public T get() {
		throw new UnsupportedOperationException("get()");
	}

	@Override
	public void send(UnaryOperator<T> transformFun) {
		throw new UnsupportedOperationException("send()");
	}

	@Override
	public CompletableFuture<T> sendAndGet(UnaryOperator<T> transformFun) {
		throw new UnsupportedOperationException("sendAndGet()");
	}

	@Override
	public CompletableFuture<T> getAsync() {
		throw new UnsupportedOperationException("getAsync()");
	}

	@Override
	public CompletableFuture<T> completeIf(Predicate<T> predicate) {
		throw new UnsupportedOperationException("completeIf()");
	}
}
