package com.nurkiewicz.java8;

import com.google.common.collect.ImmutableSet;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Implement custom Collector to ImmutableSet from Guava
 * @see ImmutableSet#builder()
 */
@Ignore
public class J08b_CustomCollectorTest {

	@Test
	public void shouldReturnEmptyImmutableSet() throws Exception {
		//given
		final Set<Integer> items = Collections.emptySet();

		//when
		final ImmutableSet<Integer> set = null; //items.stream().collect(new ImmutableSetCollector<>());

		//then
		assertThat(set).isEmpty();
	}

	@Test
	public void shouldReturnImmutableSetWithJustOneElement() throws Exception {
		//given
		final List<Integer> items = Collections.singletonList(42);

		//when
		final ImmutableSet<Integer> set = null; //items.stream().collect(new ImmutableSetCollector<>());

		//then
		assertThat(set).containsExactly(42);
	}

	@Test
	public void shouldCollectToImmutableSet() throws Exception {
		//given
		final List<Integer> items = Arrays.asList(3, 5, 2, 4, 7, 5, 3, 9, 2);

		//when
		final ImmutableSet<Integer> set = null; //items.stream().collect(new ImmutableSetCollector<>());

		//then
		assertThat(set).containsOnly(2, 3, 4, 5, 7, 9);
	}

	@Test
	public void shouldWorkInConcurrentEnvironment() throws Exception {
		//given
		final Stream<Long> longsWithDuplicates = LongStream
				.range(0, 100_000)
				.parallel()
				.mapToObj(x -> x / 2);

		//when
		final ImmutableSet<Long> set = null; //longsWithDuplicates.collect(new ImmutableSetCollector<>());

		//then
		final Set<Long> expected = LongStream
				.range(0, 100_000 / 2)
				.mapToObj(Long::valueOf)
				.collect(toSet());
		assertThat(set).isEqualTo(expected);
	}

}
