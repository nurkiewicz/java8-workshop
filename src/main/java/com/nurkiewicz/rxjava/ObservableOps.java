package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.util.Indexed;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class ObservableOps {

	public static <T> Observable<T> toObservable(CompletableFuture<T> future) {
		return Observable.create((Subscriber<? super T> subscriber) -> {
			future.handle((result, throwable) -> {
				if (throwable != null) {
					subscriber.onError(throwable);
				} else {
					subscriber.onNext(result);
					subscriber.onCompleted();
				}
				return result;
			});
		});
	}

	/**
	 * Non-blocking
	 */
	public static <T> CompletableFuture<List<T>> toCompletableFuture(Observable<T> observable) {
		final CompletableFuture<List<T>> promise = new CompletableFuture<>();
		final List<T> result = new ArrayList<>();
		observable.subscribe(
				result::add,
				promise::completeExceptionally,
				() -> promise.complete(result)
		);
		return promise;
	}

	public static <T> Observable<T> iterate(T initialValue, UnaryOperator<T> nextFun) {
		return Observable.create(new Observable.OnSubscribe<T>() {
			T value = initialValue;
			@Override
			public void call(Subscriber<? super T> subscriber) {
				while (!subscriber.isUnsubscribed()) {
					subscriber.onNext(value);
					value = nextFun.apply(value);
				}
			}
		});
	}

	public static Observable<Integer> naturals() {
		return iterate(0, x -> x + 1);
	}

	public static <T> Observable<Indexed<T>> index(Observable<T> input) {
		return input.zip(naturals(), Indexed::new);
	}

	public static <T> Observable.Operator<Indexed<T>, T> withIndex() {
		return subscriber -> new Subscriber<T>(subscriber) {
			int index = 0;
			@Override
			public void onCompleted() {
				subscriber.onCompleted();
			}

			@Override
			public void onError(Throwable e) {
				subscriber.onError(e);
			}

			@Override
			public void onNext(T t) {
				subscriber.onNext(new Indexed<>(t, index++));
			}
		};
	}
}
