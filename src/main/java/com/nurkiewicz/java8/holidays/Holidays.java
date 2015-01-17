package com.nurkiewicz.java8.holidays;

import java.time.LocalDate;
import java.util.Locale;
import java.util.stream.Stream;

public interface Holidays {

	boolean isHoliday(LocalDate date);

	/**
	 * Complementary to {@link #isHoliday(LocalDate)}.
	 * @return !{@link #isHoliday(LocalDate)}
	 */
	default boolean isWorkingDay(LocalDate date) {
		return !isHoliday(date);
	}

	default LocalDate nextHolidayAfter(LocalDate date) {
		return Stream.iterate(date.plusDays(1), d -> d.plusDays(1))
				.filter(this::isHoliday)
				.findFirst()
				.get();
	}

	default LocalDate nextWorkingDayAfter(LocalDate date) {
		return Stream.iterate(date.plusDays(1), d -> d.plusDays(1))
				.filter(this::isWorkingDay)
				.findFirst()
				.get();
	}

	static Holidays of(Locale locale) {
		switch (locale.getCountry()) {
			case "PL":
				return new PolishHolidays();
			case "US":
				return new AmericanHolidays();
			default:
				throw new IllegalArgumentException("Unsupported: " + locale.getCountry());
		}
	}

}
