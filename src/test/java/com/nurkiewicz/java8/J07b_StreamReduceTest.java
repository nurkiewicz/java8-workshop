package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.StreamTestFixture;
import com.nurkiewicz.java8.util.StreamTestFixture.StreamParallelism;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.nurkiewicz.java8.util.StreamTestFixture.PARALLEL_TEST_CASE_NAME_FORMAT;
import static com.nurkiewicz.java8.util.StreamTestFixture.changeStreamParallelism;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class J07b_StreamReduceTest {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void shouldAddNumbersUsingReduce() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final int sum = input.stream().reduce(0, (acc, x) -> acc + x);  //could be simplified to Integer::sum

		//then
		assertThat(sum).isEqualTo(2 + 3 + 5 + 7);
	}

	@Test
	public void shouldConcatNumbersBrokenWithParallelStream() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final String result = input.stream()    //WARNING: Broken with parallel stream
				.reduce(
						new StringBuilder(),
						(acc, x) -> {
							log.info("Accumulator '{}', '{}'", acc, x);
							return acc.append(x);
						},
						(sb1, sb2) -> {
							log.info("Combiner '{}', '{}'", sb1, sb2);
							return sb1.append(sb2);
						})
				.toString();

		//then
		assertThat(result).isEqualToIgnoringCase("2357");
	}

	@Test
	public void shouldConcatNumbers() throws Exception {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);

		//when
		final String result = input.stream()
				.reduce(
						new StringBuilder(),
						(acc, x) -> new StringBuilder(acc).append(x),
						(sb1, sb2) -> new StringBuilder(sb1).append(sb2))
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
	@Parameters(source = StreamTestFixture.class)
	@TestCaseName(PARALLEL_TEST_CASE_NAME_FORMAT)
	public void shouldSimulateMapUsingReduce(StreamParallelism requestedParallelism) {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 5, 7);
		Stream<Integer> stream = changeStreamParallelism(input.stream(), requestedParallelism);

		//when
		final List<Integer> doubledPrimes = stream
				.reduce(Collections.emptyList(),
						(list, x) -> {
							List<Integer> resultList = new ArrayList<>(list);
							resultList.add(x * 2);
							return resultList;
						},
						(list1, list2) -> {
							List<Integer> resultList = new ArrayList<>(list1);
							resultList.addAll(list2);
							return resultList;
						});

		//then
		assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
	}

	@Test
	@Parameters(source = StreamTestFixture.class)
	@TestCaseName(PARALLEL_TEST_CASE_NAME_FORMAT)
	public void shouldSimulateFilterUsingReduce(StreamParallelism requestedParallelism) {
		//given
		final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);
		Stream<Integer> stream = changeStreamParallelism(input.stream(), requestedParallelism);

		//when
		final List<Integer> onlyEvenNumbers = stream
				.reduce(Collections.emptyList(),
						(list, x) -> {
							List<Integer> resultList = new ArrayList<>(list);
							if (x % 2 == 0) {
								resultList.add(x);
							}
							return resultList;
						},
						(list1, list2) -> {
							List<Integer> resultList = new ArrayList<>(list1);
							resultList.addAll(list2);
							return resultList;
						}
				);

		//then
		assertThat(onlyEvenNumbers).containsExactly(2, 4, 6);
	}

}
