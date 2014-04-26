package com.nurkiewicz.java8.holidays;

import java.time.LocalDate;
import java.time.Year;

class Easter {

	static LocalDate sundayFor(Year year) {
		final int y = year.getValue();
		final int a = y % 19;
		final int b = y / 100;
		final int c = y % 100;
		final int d = b / 4;
		final int e = b % 4;
		final int f = (b + 8) / 25;
		final int g = (b - f + 1) / 3;
		final int h = (19 * a + b - d - g + 15) % 30;
		final int i = c / 4;
		final int k = c % 4;
		final int m = (32 + 2 * e + 2 * i - h - k) % 7;
		final int n = (a + 11 * h + 22 * m) / 451;
		final int month = (h + m - 7 * n + 114) / 31;
		final int day = (((h + m - (7 * n) + 114) % 31) + 1);
		return LocalDate.of(y, month, day);
	}

}
