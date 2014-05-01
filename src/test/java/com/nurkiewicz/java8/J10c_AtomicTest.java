package com.nurkiewicz.java8;

import com.nurkiewicz.java8.atomic.SafeCalculator;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;
import static org.hamcrest.Matchers.closeTo;

/**
 * *Adder
 * *Accumulator
 * Atomic* improvements
 * - Decide which atomic.* class best suits given requirements
 */
@Ignore
public class J10c_AtomicTest {

	@Test
	public void shouldReturnZeroWhenNoPreviousInteractionsWithTheCalulator() throws Exception {
		//given
		final SafeCalculator calculator = new SafeCalculator();

		//when
		final double result = calculator.doubleValue();

		//then
		assertThat(result).isEqualTo(0.0, offset(0.1));
	}

	@Test
	public void shouldCorrectlyApplyAllOperations() throws Exception {
		//given
		final SafeCalculator calculator = new SafeCalculator();

		//when
		final int i1 = calculator.add(3);      //0 -> 3
		final int i2 = calculator.sub(1);      //3 -> 2
		final int i3 = calculator.mul(4);      //2 -> 8
		final int i4 = calculator.div(2);      //8 -> 4

		//then
		assertThat(i1).isZero();
		assertThat(i2).isEqualTo(3);
		assertThat(i3).isEqualTo(2);
		assertThat(i4).isEqualTo(8);
		assertThat(calculator.intValue()).isEqualTo(4);
	}

	@Test
	public void shouldResetToGivenValueAndReturnPrevious() throws Exception {
		//given
		final SafeCalculator calculator = new SafeCalculator();

		//when
		calculator.add(4);      //4
		calculator.sub(-1);     //5
		final double tmp = calculator.set(3);   //3
		calculator.mul(2);      //6
		calculator.div(3);      //2

		//then
		assertThat(tmp).isEqualTo(3.0, offset(0.1));
		assertThat(calculator.doubleValue()).isEqualTo(2.0, offset(0.1));
	}

	@Test
	public void shouldCalculateInMultipleThreads() throws Exception {
		//given
		final SafeCalculator calculator = new SafeCalculator();

		//when
		IntStream.range(1, 5000).forEach(i ->
				MultiRunner.runMultiThreaded(() -> calculator.add(i)));

		//then
		final int expectedSum = IntStream.range(1, 5000).sum();
		await().untilCall(to(calculator).doubleValue(), closeTo(expectedSum, 0.01));
	}

}
