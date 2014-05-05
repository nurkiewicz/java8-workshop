package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
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
		final int max = 0;  //input.stream()...

		//then
		assertThat(max).isEqualTo(8);
	}

	@Test
	public void shouldSimulateMapUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final List<Integer> doubledPrimes = null;   //input.stream()...

		//then
		assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
	}

	@Test
	public void shouldSimulateFilterUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);

		//when
		final List<Integer> doubledPrimes = null;   //input.stream()...

		//then
		assertThat(doubledPrimes).containsExactly(2, 4, 6);
	}

}
