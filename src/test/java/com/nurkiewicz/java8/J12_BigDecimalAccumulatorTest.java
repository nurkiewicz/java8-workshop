package com.nurkiewicz.java8;

import com.nurkiewicz.java8.atomic.BigDecimalAccumulator;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static com.jayway.awaitility.Awaitility.await;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Implement {@link BigDecimalAccumulator} so that it satisfies tests below.
 */
@Ignore
public class J12_BigDecimalAccumulatorTest {

	@Test
	public void shouldFirstReturnInitialValue() {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ZERO, (a, x) -> a.add(x));

		//when
		final BigDecimal initial = accumulator.get();

		//then
		assertThat(initial).isEqualByComparingTo(BigDecimal.ZERO);
	}

	@Test
	public void shouldResetToInitialValue() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ONE, (a, x) -> a.multiply(x));

		//when
		accumulator.reset();

		//then
		assertThat(accumulator.get()).isEqualByComparingTo(BigDecimal.ONE);
	}

	@Test
	public void shouldReturnAndResetToInitialValue() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ONE, (a, x) -> a.multiply(x));

		//when
		final BigDecimal initial = accumulator.getAndReset();

		//then
		assertThat(initial).isEqualByComparingTo(BigDecimal.ONE);
	}

	@Test
	public void shouldAccumulateOneValue() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ZERO, (a, x) -> a.add(x));

		//when
		accumulator.accumulate(BigDecimal.TEN);

		//then
		assertThat(accumulator.get()).isEqualByComparingTo(BigDecimal.TEN);
	}

	@Test
	public void shouldAccumulateMultipleValues() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ZERO, (a, x) -> a.add(x));

		//when
		accumulator.accumulate(BigDecimal.valueOf(1));
		accumulator.accumulate(BigDecimal.valueOf(3));
		accumulator.accumulate(BigDecimal.valueOf(2));

		//then
		assertThat(accumulator.get()).isEqualByComparingTo(BigDecimal.valueOf(1 + 3 + 2));
	}

	@Test
	public void shouldAccumulateMaximum() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ZERO, (a, x) -> a.max(x));

		//when
		accumulator.accumulate(BigDecimal.valueOf(1));
		accumulator.accumulate(BigDecimal.valueOf(7));
		accumulator.accumulate(BigDecimal.valueOf(2));
		accumulator.accumulate(BigDecimal.valueOf(3));

		//then
		assertThat(accumulator.get()).isEqualByComparingTo(BigDecimal.valueOf(7));
	}

	@Test
	public void shouldCorrectlyAccumulateFromMultipleThreads() throws Exception {
		//given
		final BigDecimalAccumulator accumulator = new BigDecimalAccumulator(BigDecimal.ZERO, (a, x) -> a.add(x));
		final int count = 1000;

		//when
		MultiRunner.runMultiThreaded(count, () -> accumulator.accumulate(BigDecimal.ONE));

		//then
		await().until(() -> accumulator.get().intValue() == count);
	}

}