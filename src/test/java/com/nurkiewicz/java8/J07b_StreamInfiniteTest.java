package com.nurkiewicz.java8;

import org.junit.Test;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

/**
 * @see PrimeUtil
 */
public class J07b_StreamInfiniteTest {

	@Test
	public void shouldGenerateNaturalNumbersAndSumFirstThousand() throws Exception {
		//given
		final LongStream naturals = LongStream.iterate(1, x -> x + 1);

		//when
		final long sum = naturals.limit(1000).sum();

		//then
		assertThat(sum).isEqualTo(500500);
	}

	@Test
	public void shouldCalculateProductOfFirstFivePrimes() throws Exception {
		//given
		LongStream primes = LongStream.iterate(2, null);

		//when
		final long product = primes.limit(5).reduce(1, (acc, x) -> acc * x);

		//then
		assertThat(product).isEqualTo(2 * 3 * 5 * 7 * 11);
	}

	@Test
	public void shouldGenerateGrowingStrings() throws Exception {
		//given
		final Stream<String> starStream = Stream.iterate("", s -> s + "*");

		//when
		List<String> strings = null;

		//then
		assertThat(strings).containsExactly(
				"",
				"*",
				"**",
				"***",
				"****",
				"*****",
				"******");
	}

	/**
	 * This method tries to estimate Pi number by randomly generating points on a 2x2 square.
	 * Then it calculates what's the distance of each point to the center of the square.
	 * The proportion of points closer than 1 to all of them approximates Pi.
	 * The more points you take, better approximation you get.
	 * @see <a href="http://en.wikipedia.org/wiki/Approximations_of_π#Summing_a_circle.27s_area">Wikipedia</a>
	 */
	@Test
	public void shouldEstimatePi() throws Exception {
		//given
		Stream<Point> randomPoints = null;
		Stream<Boolean> pointsInsideUnitCircle = null;  //randomPoints...

		//when
		final double pi = 0;

		//then
		assertThat(pi).isEqualTo(Math.PI, offset(0.001));
	}

}

class Point {
	private final double x;
	private final double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}