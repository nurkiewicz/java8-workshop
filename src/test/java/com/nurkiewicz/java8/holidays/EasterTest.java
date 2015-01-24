package com.nurkiewicz.java8.holidays;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.MonthDay;
import java.time.Year;

import static java.time.Month.APRIL;
import static java.time.Month.MARCH;
import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EasterTest {

	@Test
	@Parameters
	public void shouldCalculateEasterSunday(int year, MonthDay easterDate) {
		assertThat(Easter.sundayFor(Year.of(year))).isEqualTo(easterDate.atYear(year));
	}

	private Object[] parametersForShouldCalculateEasterSunday() {
		return $(
				$(1994, MonthDay.of(APRIL, 3)),
				$(1995, MonthDay.of(APRIL, 16)),
				$(1996, MonthDay.of(APRIL, 7)),
				$(1997, MonthDay.of(MARCH, 30)),
				$(1998, MonthDay.of(APRIL, 12)),
				$(1999, MonthDay.of(APRIL, 4)),
				$(2000, MonthDay.of(APRIL, 23)),
				$(2001, MonthDay.of(APRIL, 15)),
				$(2002, MonthDay.of(MARCH, 31)),
				$(2003, MonthDay.of(APRIL, 20)),
				$(2004, MonthDay.of(APRIL, 11)),
				$(2005, MonthDay.of(MARCH, 27)),
				$(2006, MonthDay.of(APRIL, 16)),
				$(2007, MonthDay.of(APRIL, 8)),
				$(2008, MonthDay.of(MARCH, 23)),
				$(2009, MonthDay.of(APRIL, 12)),
				$(2010, MonthDay.of(APRIL, 4)),
				$(2011, MonthDay.of(APRIL, 24)),
				$(2012, MonthDay.of(APRIL, 8)),
				$(2013, MonthDay.of(MARCH, 31)),
				$(2014, MonthDay.of(APRIL, 20)),
				$(2015, MonthDay.of(APRIL, 5)),
				$(2016, MonthDay.of(MARCH, 27)),
				$(2017, MonthDay.of(APRIL, 16)),
				$(2018, MonthDay.of(APRIL, 1)),
				$(2019, MonthDay.of(APRIL, 21)),
				$(2020, MonthDay.of(APRIL, 12)),
				$(2021, MonthDay.of(APRIL, 4)),
				$(2022, MonthDay.of(APRIL, 17)),
				$(2023, MonthDay.of(APRIL, 9)),
				$(2024, MonthDay.of(MARCH, 31)),
				$(2025, MonthDay.of(APRIL, 20)),
				$(2026, MonthDay.of(APRIL, 5)),
				$(2027, MonthDay.of(MARCH, 28)),
				$(2028, MonthDay.of(APRIL, 16)),
				$(2029, MonthDay.of(APRIL, 1)),
				$(2030, MonthDay.of(APRIL, 21)),
				$(2031, MonthDay.of(APRIL, 13)),
				$(2032, MonthDay.of(MARCH, 28)),
				$(2033, MonthDay.of(APRIL, 17)),
				$(2034, MonthDay.of(APRIL, 9))
		);
	}
}