package com.nurkiewicz.java8;

import com.nurkiewicz.java8.atomic.SafeCalculator;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

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
		calculator.add(3);      //3
		calculator.sub(1);      //2
		calculator.mul(4);      //8
		calculator.div(2);      //4

		//then
		assertThat(calculator.doubleValue()).isEqualTo(4.0, offset(0.1));
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
		assertThat(calculator.doubleValue()).isEqualTo(expectedSum, offset(0.1));
	}

}
