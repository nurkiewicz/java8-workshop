package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Hint: String.chars()
 */
public class J09_StringsTest {

	@Test
	public void arrAllCharactersDigits() throws Exception {
		assertThat(onlyDigits("+12 345-678 (90)")).isFalse();
		assertThat(onlyDigits("100 200 300")).isFalse();
		assertThat(onlyDigits("1234567890")).isTrue();
	}

	private boolean onlyDigits(String phone) {
		return phone
				.chars()
				.allMatch(Character::isDigit);
	}

	@Test
	public void hasAnyNonAlphabeticCharacters() throws Exception {
		assertThat(anyNonAlphabetic("abc")).isFalse();
		assertThat(anyNonAlphabetic("CamelCase")).isFalse();
		assertThat(anyNonAlphabetic("_underscore")).isTrue();
		assertThat(anyNonAlphabetic("Big bang!")).isTrue();
		assertThat(anyNonAlphabetic("#%@")).isTrue();
	}

	private boolean anyNonAlphabetic(String s) {
		return !s
				.chars()
				.allMatch(Character::isAlphabetic);
	}

	/**
	 * Hint: String.join()
	 */
	@Test
	public void shouldJoinMultipleStringsIntoString() throws Exception {
		//given
		final List<String> ids = Arrays.asList("1", "2", "3", "4");

		//when
		final String joined = String.join(", ", ids);

		//then
		assertThat(joined).isEqualTo("1, 2, 3, 4");
	}

	@Test
	public void shouldJoinMultipleIntsIntoString() throws Exception {
		//given
		final List<Integer> ids = Arrays.asList(1, 2, 3, 4);

		//when
		final String joined = String.join(", ", ids.stream().map(Object::toString).collect(toList()));

		//then
		assertThat(joined).isEqualTo("1, 2, 3, 4");
	}

	/**
	 * Hint: StringJoiner
	 */
	@Test
	public void shouldJoinSeparateStrings() throws Exception {
		//given
		String x = "X";
		String y = "Y";
		String z = "Z";

		//when
		String joined = new StringJoiner("-", "<", ">").add(x).add(y).add(z).toString();

		//then
		assertThat(joined).isEqualTo("<X-Y-Z>");
	}

}
