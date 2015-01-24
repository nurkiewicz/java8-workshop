package com.nurkiewicz.java8.holidays;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tasks:
 * - move duplicated code to default methods in interface
 * - move factory to static interface method
 * - refactor nextWorkingDayAfter/nextHolidayAfter to avoid duplication
 */
public class PolishHolidaysTest {

	private final Holidays holidays = new PolishHolidays();

	@Test
	public void shouldIdentifyFixedPolishHolidays() {
		assertHoliday(LocalDate.of(2014, JANUARY, 1));
		assertHoliday(LocalDate.of(2014, JANUARY, 6));
		assertHoliday(LocalDate.of(2014, MAY, 1));
		assertHoliday(LocalDate.of(2014, MAY, 3));
		assertHoliday(LocalDate.of(2014, AUGUST, 15));
		assertHoliday(LocalDate.of(2014, NOVEMBER, 1));
		assertHoliday(LocalDate.of(2014, NOVEMBER, 11));
		assertHoliday(LocalDate.of(2014, DECEMBER, 25));
		assertHoliday(LocalDate.of(2014, DECEMBER, 26));
	}

	@Test
	public void shouldIdentifyWeekends() {
		assertWorkingDay(LocalDate.of(2014, APRIL, 25));
		assertHoliday(LocalDate.of(2014, APRIL, 26));
		assertHoliday(LocalDate.of(2014, APRIL, 27));
		assertWorkingDay(LocalDate.of(2014, APRIL, 28));
	}

	@Test
	public void shouldIdentifyMovingHolidays() {
		assertThat(holidays.isHoliday(LocalDate.of(2014, APRIL, 21))).isTrue();
		assertThat(holidays.isHoliday(LocalDate.of(2014, JUNE, 19))).isTrue();
	}

	@Test
	public void shouldFindNextWorkingDay() {
		assertThat(holidays.nextWorkingDayAfter(LocalDate.of(2014, APRIL, 24))).isEqualTo(LocalDate.of(2014, APRIL, 25));
		assertThat(holidays.nextWorkingDayAfter(LocalDate.of(2014, APRIL, 25))).isEqualTo(LocalDate.of(2014, APRIL, 28));
		assertThat(holidays.nextWorkingDayAfter(LocalDate.of(2014, APRIL, 26))).isEqualTo(LocalDate.of(2014, APRIL, 28));
		assertThat(holidays.nextWorkingDayAfter(LocalDate.of(2014, APRIL, 28))).isEqualTo(LocalDate.of(2014, APRIL, 29));
		assertThat(holidays.nextWorkingDayAfter(LocalDate.of(2014, APRIL, 30))).isEqualTo(LocalDate.of(2014, MAY, 2));
	}

	@Test
	public void shouldFindNextHoliday() {
		assertThat(holidays.nextHolidayAfter(LocalDate.of(2014, APRIL, 24))).isEqualTo(LocalDate.of(2014, APRIL, 26));
		assertThat(holidays.nextHolidayAfter(LocalDate.of(2014, APRIL, 25))).isEqualTo(LocalDate.of(2014, APRIL, 26));
		assertThat(holidays.nextHolidayAfter(LocalDate.of(2014, APRIL, 26))).isEqualTo(LocalDate.of(2014, APRIL, 27));
		assertThat(holidays.nextHolidayAfter(LocalDate.of(2014, APRIL, 28))).isEqualTo(LocalDate.of(2014, MAY, 1));
		assertThat(holidays.nextHolidayAfter(LocalDate.of(2014, MAY, 1))).isEqualTo(LocalDate.of(2014, MAY, 3));
	}

	private void assertHoliday(LocalDate date) {
		assertThat(holidays.isHoliday(date)).isTrue();
		assertThat(holidays.isWorkingDay(date)).isFalse();
	}

	private void assertWorkingDay(LocalDate date) {
		assertThat(holidays.isHoliday(date)).isFalse();
		assertThat(holidays.isWorkingDay(date)).isTrue();
	}

}