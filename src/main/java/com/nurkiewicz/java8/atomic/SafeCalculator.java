package com.nurkiewicz.java8.atomic;

public class SafeCalculator extends Number {

	/**
	 * Sets new value
	 * @return Previous value
	 */
	public int set(int x) {
		throw new UnsupportedOperationException("reset()");
	}

	public int mul(int x) {
		throw new UnsupportedOperationException("inc()");
	}

	public int div(int x) {
		throw new UnsupportedOperationException("div()");
	}

	public int add(int x) {
		throw new UnsupportedOperationException("add()");
	}

	public int sub(int x) {
		throw new UnsupportedOperationException("sub()");
	}

	@Override
	public int intValue() {
		throw new UnsupportedOperationException("intValue()");
	}

	@Override
	public long longValue() {
		throw new UnsupportedOperationException("longValue()");
	}

	@Override
	public float floatValue() {
		throw new UnsupportedOperationException("floatValue()");
	}

	@Override
	public double doubleValue() {
		throw new UnsupportedOperationException("doubleValue()");
	}
}
