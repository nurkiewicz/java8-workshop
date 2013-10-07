package pl.warsjawa.java8;

import org.junit.Test;
import pl.warsjawa.java8.defmethods.Engine;
import pl.warsjawa.java8.defmethods.Job;
import pl.warsjawa.java8.defmethods.Lifecycle;
import pl.warsjawa.java8.defmethods.RuleEngine;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - Conflicting default methods
 */
public class Lesson01b_ConflictingDefaultMethodsTest {

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

