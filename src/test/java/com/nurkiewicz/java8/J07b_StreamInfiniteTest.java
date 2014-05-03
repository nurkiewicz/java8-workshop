package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.PrimeUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

/**
 * @see PrimeUtil
 */
@Ignore
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
	public void shouldCheckForPrimes() throws Exception {
		assertThat(PrimeUtil.isPrime(2)).isTrue();
		assertThat(PrimeUtil.isPrime(3)).isTrue();
		assertThat(PrimeUtil.isPrime(4)).isFalse();
		assertThat(PrimeUtil.isPrime(5)).isTrue();
		assertThat(PrimeUtil.isPrime(6)).isFalse();
		assertThat(PrimeUtil.isPrime(7)).isTrue();
		assertThat(PrimeUtil.isPrime(8)).isFalse();
		assertThat(PrimeUtil.isPrime(9)).isFalse();
		assertThat(PrimeUtil.isPrime(10)).isFalse();
		assertThat(PrimeUtil.isPrime(11)).isTrue();
	}

	@Test
	public void shouldFindNextPrime() throws Exception {
		assertThat(PrimeUtil.nextPrimeAfter(2)).isEqualTo(3);
		assertThat(PrimeUtil.nextPrimeAfter(3)).isEqualTo(5);
		assertThat(PrimeUtil.nextPrimeAfter(4)).isEqualTo(5);
		assertThat(PrimeUtil.nextPrimeAfter(5)).isEqualTo(7);
		assertThat(PrimeUtil.nextPrimeAfter(6)).isEqualTo(7);
		assertThat(PrimeUtil.nextPrimeAfter(7)).isEqualTo(11);
		assertThat(PrimeUtil.nextPrimeAfter(8)).isEqualTo(11);
		assertThat(PrimeUtil.nextPrimeAfter(9)).isEqualTo(11);
		assertThat(PrimeUtil.nextPrimeAfter(10)).isEqualTo(11);
		assertThat(PrimeUtil.nextPrimeAfter(11)).isEqualTo(13);
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
		final Stream<String> starStream = Stream.iterate("", null);

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

		//when
		final double piDividedByFour = 0;

		//then
		assertThat(piDividedByFour * 4).isEqualTo(Math.PI, offset(0.001));
	}

}

class Point {
	private final double x;
	private final double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static Point random() {
		return new Point(Math.random() * 2 - 1, Math.random() * 2 - 1);
	}

	public double distance() {
		return Math.sqrt(x * x + y * y);
	}

}