package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * - covariance of arrays
 * - input/output
 * - function
 */
@Ignore
public class J60_CovarianceTest {

	@Test
	public void variance() throws ExecutionException, InterruptedException {
	}

	public double sum(List<Number> numbers) {
		return numbers
				.stream()
				.mapToDouble(Number::doubleValue)
				.sum();
	}

	public static <T> void sort(List<T> list, Comparator<T> c) {
		Collections.sort(list, c);
	}
}

class NumberComparator implements Comparator<Number> {

	@Override
	public int compare(Number o1, Number o2) {
		return Double.compare(o1.doubleValue(), o2.doubleValue());
	}
}