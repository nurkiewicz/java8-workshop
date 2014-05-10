package com.nurkiewicz.java8.defmethods;

public class RuleEngine implements Job, Engine, Lifecycle {

	public String start() {
		return Job.super.start();
	}
}
