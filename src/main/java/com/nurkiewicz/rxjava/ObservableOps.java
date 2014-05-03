package com.nurkiewicz.rxjava;

import rx.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

}
