package com.nurkiewicz.java8.util;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.CharMatcher.anyOf;

/**
 * @see Map#merge(Object, Object, BiFunction)
 * @see Map#computeIfAbsent(Object, Function)
 * @see Collectors#groupingBy(Function)
 */
public class LoremIpsum {

	public static String text() throws IOException {
		return IOUtils.toString(LoremIpsum.class.getResourceAsStream("/lorem-ipsum.txt"));
	}

	/**
	 * Case insensitive
	 */
	public static Map<String, Integer> wordCount(String text) {
		final List<String> words = splitWords(text);
		Map<String, Integer> wordCount = new HashMap<>();
		for (String word : words) {
			wordCount.merge(word.toLowerCase(), 1, (x, one) -> x + one);
		}
		return wordCount;
	}

	private static List<String> splitWords(String text) {
		return Splitter
				.on(anyOf(" .,\n"))
				.trimResults()
				.omitEmptyStrings()
				.splitToList(text);
	}

}
