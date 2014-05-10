package com.nurkiewicz.java8.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCalculator extends Number {

	private final AtomicInteger value = new AtomicInteger();

	/**
	 * Sets new value
	 * @return Previous value
	 */
	public int set(int x) {
		return value.getAndSet(x);
	}

	public int mul(int x) {
		return value.getAndUpdate(prev -> prev * x);
	}

	public int div(int x) {
		return value.getAndUpdate(prev -> prev / x);
	}

	public int add(int x) {
		return value.getAndAdd(x);
	}

	public int sub(int x) {
		return value.getAndAdd(-x);
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}
}
