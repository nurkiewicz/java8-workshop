package com.nurkiewicz.java8;

import com.nurkiewicz.java8.atomic.RangeCollector;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

/**
 * *Adder
 * *Accumulator
 * Atomic* improvements
 * - Decide which atomic.* class best suits given requirements
 */
@Ignore
public class J10b_AtomicTest {

	@Test
	public void shouldHaveExtremeRangesInTheBeginning() throws Exception {
		//given
		final RangeCollector range = new RangeCollector();

		//when
		final double min = range.getMin();
		final double max = range.getMax();

		//then
		assertThat(min).isEqualTo(Double.MAX_VALUE);
		assertThat(max).isEqualTo(Double.MIN_VALUE);
	}

	@Test
	public void shouldCountSingleValueAsBothMinAndMax() throws Exception {
		//given
		final RangeCollector range = new RangeCollector();
		final int someValue = 42;

		//when
		range.save(someValue);

		//then
		assertThat(range.getMin()).isEqualTo(someValue, offset(0.1));
		assertThat(range.getMax()).isEqualTo(someValue, offset(0.1));
	}

	@Test
	public void shouldRememberMinAndMaxOfMultipleValues() throws Exception {
		//given
		final RangeCollector range = new RangeCollector();

		//when
		range.save(3);
		range.save(1);
		range.save(5);
		range.save(-1);
		range.save(2);

		//then
		assertThat(range.getMin()).isEqualTo(-1, offset(0.1));
		assertThat(range.getMax()).isEqualTo(5, offset(0.1));
	}

	@Test
	public void shouldRememberMinAndMaxInMultipleThreads() throws Exception {
		//given
		final RangeCollector range = new RangeCollector();
		final Random random = new Random();

		//when
		MultiRunner.runMultiThreaded(1000, () -> range.save(random.nextInt() % 10));

		//then
		assertThat(range.getMin()).isEqualTo(0, offset(0.1));
		assertThat(range.getMax()).isEqualTo(9, offset(0.1));
	}

}
