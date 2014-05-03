package com.nurkiewicz.java8.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Map;

public class LoremIpsum {

	public static String text() throws IOException {
		return IOUtils.toString(LoremIpsum.class.getResourceAsStream("/lorem-ipsum.txt"));
	}

	public static Map<String, Integer> wordCount(String text) {
		throw new UnsupportedOperationException("calcWordCount()");
	}

}
