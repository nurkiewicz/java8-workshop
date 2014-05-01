package com.nurkiewicz.java8.defmethods;


public interface Job {

	/**
	 * Do not TOUCH!
	 */
	default String start() {
		return "Job";
	}

}
