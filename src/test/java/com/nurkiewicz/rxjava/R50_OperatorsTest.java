package com.nurkiewicz.rxjava;

import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.function.Predicate;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
public class R50_OperatorsTest {

	@Test
	public void shouldRejectItemsNotGreaterThanMaxSoFar() throws Exception {
		//given
		final Observable<Integer> observable = Observable.from(1, 2, 3, 2, 5, 4, 7, 5, 6, 7, 8, 9, 5, 6, 9, 10, 9, 12);

		//when
		final Observable<Integer> maxObservable = observable;       //TODO

		//then
		final List<Integer> filtered = Lists.newArrayList(maxObservable.toBlockingObservable().toIterable());
		assertThat(filtered).containsExactly(1, 2, 3, 5, 7, 8, 9, 10, 12);
	}

	@Test
	public void shouldFilterUsingCustomOperator() throws Exception {
		final Observable<Integer> observable = Observable.from(1, 2, 3, 2, 5, 4, 7, 5, 6, 7, 8, 9, 5, 6, 9, 10, 9, 12);

		//when
		final Observable<Integer> maxObservable = observable.lift(myFilter(x -> x < 5));

		//then
		final List<Integer> filtered = Lists.newArrayList(maxObservable.toBlockingObservable().toIterable());
		assertThat(filtered).containsExactly(1, 2, 3, 2, 4);
	}

	private Observable.Operator<Integer, Integer> myFilter(Predicate<Integer> predicate) {
		return new MyFilterOperator(predicate);
	}

	@Test
	public void shouldRejectItemsNotGreaterThanMaxSoFarUsingCustomOperator() throws Exception {
		//given
		final Observable<Integer> observable = Observable.from(1, 2, 3, 2, 5, 4, 7, 5, 6, 7, 8, 9, 5, 6, 9, 10, 9, 12);

		//when
		final Observable<Integer> maxObservable = observable.lift(onlyGreater());

		//then
		final List<Integer> filtered = Lists.newArrayList(maxObservable.toBlockingObservable().toIterable());
		assertThat(filtered).containsExactly(1, 2, 3, 5, 7, 8, 9, 10, 12);
	}

	private Observable.Operator<Integer, Integer> onlyGreater() {
		throw new UnsupportedOperationException("onlyGreater()");
	}

}

class MyFilterOperator implements Observable.Operator<Integer, Integer> {
	public MyFilterOperator(Predicate<Integer> predicate) {
	}

	@Override
	public Subscriber<? super Integer> call(Subscriber<? super Integer> subscriber) {
		return new Subscriber<Integer>() {
			@Override
			public void onCompleted() {
				subscriber.onCompleted();
			}

			@Override
			public void onError(Throwable e) {
				subscriber.onError(e);
			}

			@Override
			public void onNext(Integer integer) {
				subscriber.onNext(integer);
			}
		};
	}
}
