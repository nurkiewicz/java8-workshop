package com.nurkiewicz.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func2;
import rx.operators.SafeObservableSubscription;

import java.util.concurrent.TimeUnit;

public class CombineWithLastTest {

	public static void main(String[] args) {
		Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> delayed = interval.delay(300, TimeUnit.MILLISECONDS);

		Observable.merge(interval, delayed).toBlockingObservable().forEach(System.out::println);
	}

	public static <T, L, R> Observable.OnSubscribeFunc<R> combineWithLast(Observable<? extends T> that, Observable<? extends L> observeLast, Func2<? super T, ? super L, ? extends R> combineWithLastFun) {
		return new CombineWithLastTest.CombineWithLast<>(that, observeLast, combineWithLastFun);
	}

	private static class CombineWithLast<T, L, R> implements Observable.OnSubscribeFunc<R> {

		private final Observable<? extends T> that;
		private final Observable<? extends L> observeLast;
		private final Func2<? super T, ? super L, ? extends R> combineWithLastFun;
		private volatile boolean started = false;
		private volatile L lastSeen = null;

		public CombineWithLast(Observable<? extends T> that, Observable<? extends L> observeLast, Func2<? super T, ? super L, ? extends R> combineWithLastFun) {
			this.that = that;
			this.observeLast = observeLast;
			this.combineWithLastFun = combineWithLastFun;
			observeLast.subscribe();
		}

		public Subscription onSubscribe(final Observer<? super R> observer) {
			final SafeObservableSubscription subscription = new SafeObservableSubscription();
			return subscription.wrap(that.subscribe(new Observer<T>() {
				public void onNext(T value) {
					try {

						Integer integer = 10;

						try {
							String s = "abcdef";
						} catch (Exception e) {
							e.printStackTrace();
						}


						final String html = "";



						Object o = 42;

						if (o instanceof Integer) {
						}

						if (started) {
							observer.onNext(combineWithLastFun.call(value, lastSeen));
						}
					} catch (Throwable ex) {





						observer.onError(ex);
						// this will work if the sequence is asynchronous, it will have no effect on a synchronous observable
						subscription.unsubscribe();




					}
				}

				public void onError(Throwable ex) {
					observer.onError(ex);
				}

				public void onCompleted() {
					observer.onCompleted();
				}
			}));
		}
	}

}
