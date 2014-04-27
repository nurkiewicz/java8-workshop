package com.nurkiewicz.java8;

import com.nurkiewicz.java8.holidays.Holidays;
import com.nurkiewicz.java8.holidays.PolishHolidays;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.stream.Stream;

import static java.time.Month.MAY;
import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
public class J08_LocalDateTest {

	private final Holidays holidays = new PolishHolidays();

	/**
	 * Hint: consider iterate(), limit() and filter()
	 * @throws Exception
	 */
	@Test
	public void shouldCountNumberOfHolidaysIn2014() throws Exception {
		//given
		final Stream<LocalDate> holidaysIn2014 = null;

		//when
		final long numberOfHolidays = holidaysIn2014.count();

		//then
		assertThat(numberOfHolidays).isEqualTo(113);
	}

	@Test
	public void shouldApplyCustomTemporalAdjuster() throws Exception {
		//given
		final LocalDate today = LocalDate.of(2014, MAY, 12);

		//when
		final LocalDate nextHoliday = today.with(nextHoliday());

		//then
		assertThat(nextHoliday).isEqualTo(LocalDate.of(2014, MAY, 17));
	}

	public TemporalAdjuster nextHoliday() {
		throw new UnsupportedOperationException("nextHoliday()");
	}

}
