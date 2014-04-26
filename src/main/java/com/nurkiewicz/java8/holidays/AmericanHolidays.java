package com.nurkiewicz.java8.holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AmericanHolidays implements Holidays {
	@Override
	public boolean isHoliday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
				date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	@Override
	public boolean isWorkingDay(LocalDate date) {
		return !isHoliday(date);
	}

	@Override
	public LocalDate nextHolidayAfter(LocalDate date) {
		LocalDate cur = date;
		do {
			cur = cur.plusDays(1);
		} while (isWorkingDay(cur));
		return cur;
	}

	@Override
	public LocalDate nextWorkingDayAfter(LocalDate date) {
		LocalDate cur = date;
		do {
			cur = cur.plusDays(1);
		} while (isHoliday(cur));
		return cur;
	}
}
