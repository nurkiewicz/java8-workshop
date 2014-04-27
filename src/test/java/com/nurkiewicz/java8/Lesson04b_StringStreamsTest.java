package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - String.chars()
 */
@Ignore
public class Lesson04b_StringStreamsTest {

	@Test
	public void removeNonDigitsFromPhoneNumber() throws Exception {
		//given
		final String phone = "+12 345-678 (90)";

		//when
		final String sanitized = "";  //phone.chars()...

		//then
		assertThat(sanitized).isEqualTo("1234567890");
	}

	@Test
	public void shouldTurnCamelCaseToUnderscores() throws Exception {
		//given
		final String input = "thisIsVariable";

		//when
		final String result = "";  //input.chars()...

		//then
		assertThat(result).isEqualTo("this_is_variable");
	}

}
