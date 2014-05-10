package com.nurkiewicz.rxjava.util;

public class Indexed<T> {

	private final T value;
	private final int index;

	public Indexed(T value, int index) {
		this.value = value;
		this.index = index;
	}

	public T getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}
}
