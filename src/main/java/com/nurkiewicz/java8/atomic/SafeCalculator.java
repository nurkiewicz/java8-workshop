package com.nurkiewicz.java8.atomic;

public class SafeCalculator extends Number {

	/**
	 * Sets new value
	 * @return Previous value
	 */
	public double set(double x) {
		throw new UnsupportedOperationException("reset()");
	}

	public double mul(double x) {
		throw new UnsupportedOperationException("inc()");
	}

	public double div(double x) {
		throw new UnsupportedOperationException("div()");
	}

	public double add(double x) {
		throw new UnsupportedOperationException("add()");
	}

	public double sub(double x) {
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
