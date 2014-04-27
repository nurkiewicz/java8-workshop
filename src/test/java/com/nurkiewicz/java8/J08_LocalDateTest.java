package com.nurkiewicz.java8;

import com.nurkiewicz.java8.holidays.Holidays;
import com.nurkiewicz.java8.holidays.PolishHolidays;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
public class J08_LocalDateTest {

	/**
	 * Hint: consider iterate(), limit() and filter()
	 * @throws Exception
	 */
	@Test
	public void shouldCountNumberOfHolidaysIn2014() throws Exception {
		//given
		final Holidays holidays = new PolishHolidays();
		final Stream<LocalDate> holidaysIn2014 = null;

		//when
		final long numberOfHolidays = holidaysIn2014.count();

		//then
		assertThat(numberOfHolidays).isEqualTo(113);
	}

}
