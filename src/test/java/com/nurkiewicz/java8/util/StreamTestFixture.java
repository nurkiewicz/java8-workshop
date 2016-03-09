package com.nurkiewicz.java8.util;

import java.util.stream.Stream;

import static junitparams.JUnitParamsRunner.$;

public class StreamTestFixture {

	public static final String PARALLEL_TEST_CASE_NAME_FORMAT = "[{index}] {method} ({0})";

	public enum StreamParallelism {
		SEQUENTIAL, PARALLEL;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	@SuppressWarnings("unused")
	public static Object[] provideTrueFalse() {
		return $(StreamParallelism.SEQUENTIAL, StreamParallelism.PARALLEL);
	}

	public static <T> Stream<T> changeStreamParallelism(Stream<T> stream, StreamParallelism requestedParallelism) {
		//StreamParallelism could have a field with Function<Stream<?>, Stream<?>> to eliminate switch-case,
		// but the implementation looks awkward
		switch (requestedParallelism) {
			case SEQUENTIAL:
				return stream.sequential();
			case PARALLEL:
				return stream.parallel();
			default:
				throw new IllegalArgumentException("Unexpected enum value: " + requestedParallelism);
		}
	}
}
