package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.util.Indexed;
import rx.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class ObservableOps {

	public static <T> Observable<T> toObservable(CompletableFuture<T> future) {
		throw new UnsupportedOperationException("toObservable()");
	}

	/**
	 * Non-blocking
	 */
	public static <T> CompletableFuture<List<T>> toCompletableFuture(Observable<T> observable) {
		throw new UnsupportedOperationException("toCompletableFuture()");
	}

	public static <T> Observable<T> iterate(T initialValue, UnaryOperator<T> nextFun) {
		throw new UnsupportedOperationException("iterate()");
	}

	public static Observable<Integer> naturals() {
		throw new UnsupportedOperationException("naturals()");
	}

	public static <T> Observable<Indexed<T>> index(Observable<T> input) {
		throw new UnsupportedOperationException("index()");
	}

	public static <T> Observable.Operator<T, Indexed<T>> withIndex() {
		throw new UnsupportedOperationException("withIndex()");
	}
}
