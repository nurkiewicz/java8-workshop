package com.nurkiewicz.java8.holidays;

import java.util.Locale;

public class HolidaysFactory {

	public static Holidays of(Locale locale) {
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
