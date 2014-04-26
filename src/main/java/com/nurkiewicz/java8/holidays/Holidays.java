package com.nurkiewicz.java8.holidays;

import java.time.LocalDate;

public interface Holidays {

	boolean isHoliday(LocalDate date);

	/**
	 * Complementary to {@link #isHoliday(LocalDate)}.
	 * @return !{@link #isHoliday(LocalDate)}
	 */
	boolean isWorkingDay(LocalDate date);

	LocalDate nextHolidayAfter(LocalDate date);

	LocalDate nextWorkingDayAfter(LocalDate date);

}
