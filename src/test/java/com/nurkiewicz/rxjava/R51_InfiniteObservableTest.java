package com.nurkiewicz.rxjava;

import com.google.common.collect.Lists;
import com.nurkiewicz.rxjava.util.Indexed;
import org.junit.Test;
import rx.Observable;

import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static com.nurkiewicz.rxjava.ObservableOps.withIndex;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * <p>Implement infinite Observable similar to {@link Stream#iterate(Object, UnaryOperator)}
 * <p>Use this stream to map from {@link Observable} of type T to {@link Observable} of type Indexed&lt;T&gt;
 * <p>Use custom Observable operator to implement Indexed&lt;T&gt; as well
 */
public class R51_InfiniteObservableTest {

	@Test
	public void shouldCreateObservableByIteratingOverInitialElement() throws Exception {
		//given
		final Observable<Integer> iterate = ObservableOps.iterate(1, x -> x * 2);

		//when
		final Observable<Integer> stream = iterate.take(5);

		//then
		final ArrayList<Integer> items = Lists.newArrayList(stream.toBlockingObservable().toIterable());
		assertThat(items).containsExactly(1, 2, 4, 8, 16);
	}

	@Test
	public void shouldCreateInfiniteSequenceOfNaturalNumbers() throws Exception {
		//given
		final Observable<String> iterate = ObservableOps.iterate("", s -> s + "*");

		//when
		final Observable<String> stream = iterate.take(5);

		//then
		final ArrayList<String> items = Lists.newArrayList(stream.toBlockingObservable().toIterable());
		assertThat(items).containsExactly("", "*", "**", "***", "****");
	}

	@Test
	public void shouldGenerateNaturalNumbers() throws Exception {
		//given
		final Observable<Integer> naturals = ObservableOps.naturals();

		//when
		Observable<Integer> stream = naturals.take(5);

		//then
		final ArrayList<Integer> items = Lists.newArrayList(stream.toBlockingObservable().toIterable());
		assertThat(items).containsExactly(0, 1, 2, 3, 4);
	}

	@Test
	public void shouldSkipFirstThousandNaturalNumbersAndThenSkipEven() throws Exception {
		//given
		final Observable<Integer> naturals = ObservableOps.naturals();

		//when
		final Observable<Integer> stream = naturals
				.skip(1000)
				.filter(x -> x % 2 == 0)
				.take(5);

		//then
		final ArrayList<Integer> items = Lists.newArrayList(stream.toBlockingObservable().toIterable());
		assertThat(items).containsExactly(1000, 1002, 1004, 1006, 1008);
	}

	@Test
	public void shouldIndexInputStream() throws Exception {
		//given
		final Observable<String> input = Observable.from("A", "B", "C");

		//when
		final Observable<Indexed<String>> indexed = ObservableOps.index(input);
		final Observable<String> joined = indexed.map(i -> i.getValue() + ":" + i.getIndex());

		//then
		final ArrayList<String> items = Lists.newArrayList(joined.toBlockingObservable().toIterable());
		assertThat(items).containsExactly("A:0", "B:1", "C:2");
	}

	@Test
	public void shouldIndexInputSequencyByApplyingCustomOperator() throws Exception {
		//given
		final Observable<String> infinite = Observable.from("X", "Y", "Z").repeat();

		//when
		final Observable<Indexed<String>> indexed = infinite.lift(withIndex());
		final Observable<String> joined = indexed.map(i -> i.getValue() + ":" + i.getIndex()).take(7);

		//then
		final ArrayList<String> items = Lists.newArrayList(joined.toBlockingObservable().toIterable());
		assertThat(items).containsExactly("X:0", "Y:1", "Z:2", "X:3", "Y:4", "Z:5", "X:6");
	}

}
