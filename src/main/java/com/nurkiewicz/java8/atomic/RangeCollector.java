package com.nurkiewicz.java8.atomic;

import java.util.concurrent.atomic.DoubleAccumulator;

public class RangeCollector {

	private final DoubleAccumulator min = new DoubleAccumulator(Double::min, Double.MAX_VALUE);
	private final DoubleAccumulator max = new DoubleAccumulator(Double::max, Double.MIN_VALUE);

	public void save(double x) {
		min.accumulate(x);
		max.accumulate(x);
	}

	public double getMin() {
		return min.doubleValue();
	}

	public double getMax() {
		return max.doubleValue();
	}

}
