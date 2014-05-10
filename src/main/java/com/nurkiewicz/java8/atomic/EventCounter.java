package com.nurkiewicz.java8.atomic;

import java.util.concurrent.atomic.LongAdder;

public class EventCounter extends Number {

	private LongAdder accumulator = new LongAdder();

	public void incBy(long x) {
		accumulator.add(x);
	}

	public long reset() {
		return accumulator.sumThenReset();
	}

	@Override
	public int intValue() {
		return accumulator.intValue();
	}

	@Override
	public long longValue() {
		return accumulator.longValue();
	}

	@Override
	public float floatValue() {
		return accumulator.floatValue();
	}

	@Override
	public double doubleValue() {
		return accumulator.doubleValue();
	}
}
