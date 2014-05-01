package com.nurkiewicz.java8;

import com.nurkiewicz.java8.holidays.Holidays;
import com.nurkiewicz.java8.holidays.PolishHolidays;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
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

	/**
	 * @see TemporalAdjusters
	 */
	@Test
	public void shouldApplyBuiltIntTemporalAdjuster() throws Exception {
		//given
		final LocalDate today = LocalDate.of(2014, MAY, 12);

		//when
		final LocalDate previousWednesday = today;

		//then
		assertThat(previousWednesday).isEqualTo(LocalDate.of(2014, MAY, 7));
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

	/**
	 * - Calculate time after billion seconds from given date
	 * - What will be the hour in  Asia/Tokyo"
	 * - See how period can be calculated
	 * @throws Exception
	 */
	@Test
	public void shouldFindWhenIhaveBillionthSecondBirthday() throws Exception {
		//given
		final LocalDate dateOfBirth = LocalDate.of(1985, DECEMBER, 25);
		final LocalTime timeOfBirth = LocalTime.of(22, 10);
		final ZonedDateTime birth = ZonedDateTime.of(dateOfBirth, timeOfBirth, ZoneId.of("Europe/Warsaw"));

		//when
		final ZonedDateTime billionSecondsLater = birth;
		final int hourInTokyo = billionSecondsLater.getHour();

		//then
		final Period periodToBillionth = Period.between(
				LocalDate.of(2014, MAY, 12),
				billionSecondsLater.toLocalDate());
		assertThat(billionSecondsLater.toLocalDate()).isEqualTo(LocalDate.of(2017, SEPTEMBER, 3));
		assertThat(hourInTokyo).isEqualTo(7);
		assertThat(periodToBillionth.getYears()).isEqualTo(3);
		assertThat(periodToBillionth.getMonths()).isEqualTo(3);
	}

}
