package com.nurkiewicz.java8;

import com.nurkiewicz.java8.defmethods.Engine;
import com.nurkiewicz.java8.defmethods.Job;
import com.nurkiewicz.java8.defmethods.Lifecycle;
import com.nurkiewicz.java8.defmethods.RuleEngine;
import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - Conflicting default methods
 */
@Ignore
public class J02b_ConflictingDefaultMethodsTest {

	private final RuleEngine ruleEngine = new RuleEngine();

	@Test
	public void shouldExtendFewInterfaces() {
		assertThat(ruleEngine).isInstanceOf(Job.class);
		assertThat(ruleEngine).isInstanceOf(Engine.class);
		assertThat(ruleEngine).isInstanceOf(Lifecycle.class);
	}

	@Test
	public void shouldReturnValueFromJob() {
		assertThat(ruleEngine.start()).isEqualTo(2);
	}

}

