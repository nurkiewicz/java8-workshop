package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Hint: String.chars()
 */
@Ignore
public class J05b_StringsTest {

	@Test
	public void arrAllCharactersDigits() throws Exception {
		assertThat(onlyDigits("+12 345-678 (90)")).isFalse();
		assertThat(onlyDigits("100 200 300")).isFalse();
		assertThat(onlyDigits("1234567890")).isTrue();
	}

	private boolean onlyDigits(String phone) {
		return true;
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
		return true;
	}

}
