package com.nurkiewicz.java8;

import com.nurkiewicz.java8.atomic.EventCounter;
import org.junit.Test;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * *Adder
 * *Accumulator
 * Atomic* improvements
 * - Decide which atomic.* class best suits given requirements
 */
public class J10_AtomicTest {

	@Test
	public void shouldCountMultipleEvents() throws Exception {
		//given
		final EventCounter counter = new EventCounter();

		//when
		counter.incBy(1);
		counter.incBy(3);
		counter.incBy(2);

		//then
		assertThat(counter.longValue()).isEqualTo(1 + 3 + 2);
	}

	@Test
	public void shouldResetAndRememberOldValue() throws Exception {
		//given
		final EventCounter counter = new EventCounter();

		//when
		counter.incBy(1);
		counter.incBy(2);
		final long last = counter.reset();

		//then
		assertThat(counter.longValue()).isZero();
		assertThat(last).isEqualTo(1 + 2);
	}

	@Test
	public void shouldCountEventsInMultipleThreads() throws Exception {
		//given
		final EventCounter counter = new EventCounter();

		//when
		MultiRunner.runMultiThreaded(1000, () -> counter.incBy(1));

		//then
		await().untilCall(to(counter).longValue(), is(1000L));
	}

}
