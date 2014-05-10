package com.nurkiewicz.java8.util;

import java.util.stream.LongStream;

public class PrimeUtil {

	public static long nextPrimeAfter(long x) {
		return LongStream
				.iterate(x + 1, a -> a + 1)
				.filter(PrimeUtil::isPrime)
				.findFirst()
				.getAsLong();
	}

	public static boolean isPrime(long x) {
		final long upTo = Math.min(x - 1, (long) Math.ceil(Math.sqrt(x)));
		return LongStream
				.rangeClosed(2, upTo)
				.allMatch(div -> x % div != 0);
	}

}
