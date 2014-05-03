package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.LoremIpsum;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

public class J08_NewMapMethodsTest {

	@Test
	public void shouldReturnWordCount() throws Exception {
		//given
		final String loremIpsum = LoremIpsum.text();

		//when
		Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

		//then
		assertThat(wordCount)
				.hasSize(142)
				.contains(
						entry("eget", 12),
						entry("sit", 9),
						entry("amet", 9),
						entry("et", 8)
				);

		assertThat(wordCount.getOrDefault("kot", 0)).isZero();
	}

	@Test
	public void shouldReturnTotalWords() throws Exception {
		//given
		final String loremIpsum = LoremIpsum.text();
		Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

		//when
		int totalWords = 0;  //wordCount...

		//then
		assertThat(totalWords).isEqualTo(441);
	}

	@Test
	public void shouldReturnFourMostCommonWords() throws Exception {
		final String loremIpsum = LoremIpsum.text();
		Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

		//when
		final List<String> fiveMostCommon = null;   //wordCount...

		//then
		assertThat(fiveMostCommon).containsExactly("eget", "sit", "amet", "et", "sed");
	}

	@Test
	public void shouldReturnWordsThatOccurOnlyOnce() throws Exception {
		final String loremIpsum = LoremIpsum.text();
		Map<String, Integer> wordCount = LoremIpsum.wordCount(loremIpsum);

		//when
		final Set<String> uniqueWords = null;       //wordCount...

		//then
		assertThat(uniqueWords).containsOnly("abc");
	}

}
