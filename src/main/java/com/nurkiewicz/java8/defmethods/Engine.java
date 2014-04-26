package com.nurkiewicz.java8.defmethods;

public interface Engine {

	/**
	 * Do not TOUCH!
	 */
	default int start() {
		return 3;
	}

}

