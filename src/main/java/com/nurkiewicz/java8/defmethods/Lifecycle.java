package com.nurkiewicz.java8.defmethods;

public interface Lifecycle {

	default int start() {
		return 1;
	}

}
