package com.nurkiewicz.java8.holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AmericanHolidays implements Holidays {
	@Override
	public boolean isHoliday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
				date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}
}
