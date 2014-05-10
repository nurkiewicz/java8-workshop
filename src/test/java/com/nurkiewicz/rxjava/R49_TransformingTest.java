package com.nurkiewicz.rxjava;

import org.junit.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class R49_TransformingTest {

	@Test
	public void shouldGroupItemsInBatchesOfSameSize() throws Exception {
		//given
		final Observable<Integer> observable = Observable.range(1, 10);

		//when
		final Observable<List<Integer>> grouped = observable.buffer(3, 3);

		//then
		final Iterable<List<Integer>> groups = grouped.toBlockingObservable().toIterable();
		assertThat(groups).containsExactly(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5, 6),
				Arrays.asList(7, 8, 9),
				Arrays.asList(10)
		);
	}

	@Test
	public void shouldGenerateSlidingWindowOverItems() throws Exception {
		//given
		final Observable<Integer> observable = Observable.range(1, 5);

		//when
		final Observable<List<Integer>> grouped = observable.buffer(3, 1);

		//then
		final Iterable<List<Integer>> groups = grouped.toBlockingObservable().toIterable();

		assertThat(groups).containsExactly(
				Arrays.asList(1, 2, 3),
				Arrays.asList(2, 3, 4),
				Arrays.asList(3, 4, 5),
				Arrays.asList(4, 5),
				Arrays.asList(5)
		);
	}

	@Test
	public void shouldAccumulateValues() throws Exception {
		//given
		final Observable<Integer> observable = Observable.range(1, 4);

		//when
		final Observable<Integer> accumulated = observable.scan((x, y) -> x + y);

		//then
		final Iterable<Integer> result = accumulated.toBlockingObservable().toIterable();
		assertThat(result).containsExactly(
				1,
				1 + 2,
				1 + 2 + 3,
				1 + 2 + 3 + 4);
	}
}
