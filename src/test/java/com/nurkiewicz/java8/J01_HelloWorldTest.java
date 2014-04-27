package com.nurkiewicz.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - Smoke test
 */
public class J01_HelloWorldTest {

	@Test
	public void hello() {
		final List<Integer> input = Arrays.asList(2, 3, 5, 7, 11);

		final List<Integer> output = input.
				stream().
				map(i -> i * 2).
				collect(toList());

		assertThat(output).containsExactly(4, 6, 10, 14, 22);
	}

}
