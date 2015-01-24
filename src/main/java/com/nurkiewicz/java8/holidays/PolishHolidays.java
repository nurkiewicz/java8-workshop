package com.nurkiewicz.java8.holidays;

import com.google.common.collect.ImmutableSet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.Set;

import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

public class PolishHolidays implements Holidays {

	private static final Set<MonthDay> FIXED_HOLIDAYS = ImmutableSet.of(
			MonthDay.of(JANUARY, 1),
			MonthDay.of(JANUARY, 6),
			MonthDay.of(MAY, 1),
			MonthDay.of(MAY, 3),
			MonthDay.of(AUGUST, 15),
			MonthDay.of(NOVEMBER, 1),
			MonthDay.of(NOVEMBER, 11),
			MonthDay.of(DECEMBER, 25),
			MonthDay.of(DECEMBER, 26)
	);

	public boolean isHoliday(LocalDate date) {
		return isWeekend(date) ||
				isFixedHoliday(date) ||
				isEasterMonday(date) ||
				isCorpusChristi(date);
	}

	private boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
				date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	private boolean isFixedHoliday(LocalDate date) {
		return FIXED_HOLIDAYS.contains(MonthDay.from(date));
	}

	private boolean isEasterMonday(LocalDate date) {
		LocalDate easterThatYear = Easter.sundayFor(Year.of(date.getYear()));
		return date.equals(easterThatYear.plusDays(1));
	}

	private boolean isCorpusChristi(LocalDate date) {
		LocalDate easterThatYear = Easter.sundayFor(Year.of(date.getYear()));
		return date.equals(easterThatYear.plusDays(60));
	}
}
