package com.nurkiewicz.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class J07b_StreamReduceTest {

	@Test
	public void shouldAddNumbersUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final int sum = input.stream().reduce(0, (acc, x) -> acc + x);

		//then
		assertThat(sum).isEqualTo(2 + 3 + 5 + 7);
	}

	@Test
	public void shouldConcatNumbers() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final String result = input
				.stream()
				.reduce(
						new StringBuilder(),
						(acc, x) -> acc.append(x),
						(sb1, sb2) -> sb1.append(sb2))
				.toString();

		//then
		assertThat(result).isEqualToIgnoringCase("2357");
	}

	@Test
	public void shouldFindMaxUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(4, 2, 6, 3, 8, 1);

		//when
		final int max = input
				.stream()
				.reduce(Integer.MIN_VALUE, Math::max);

		//then
		assertThat(max).isEqualTo(8);
	}

	@Test
	public void shouldSimulateMapUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final List<Integer> doubledPrimes = input
				.stream()
				.reduce(
						new ArrayList<>(),
						(list, x) -> {
							list.add(x * 2);
							return list;
						},
						(list1, list2) -> {
							list1.addAll(list2);
							return list1;
						}
				);

		//then
		assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
	}

	@Test
	public void shouldSimulateFilterUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);

		//when
		final List<Integer> onlyEvenNumbers = input
				.stream()
				.reduce(
						new ArrayList<>(),
						(list, x) -> {
							if (x % 2 == 0) {
								list.add(x);
							}
							return list;
						},
						(list1, list2) -> {
							list1.addAll(list2);
							return list1;
						}
				);

		//then
		assertThat(onlyEvenNumbers).containsExactly(2, 4, 6);
	}

}
