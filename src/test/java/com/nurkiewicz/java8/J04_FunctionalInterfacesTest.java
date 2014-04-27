package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * What is a functional interface? @FunctionalInterface
 * Using lambdas instead of plain old Java classes (JButton)
 * - Implement functional interfaces using lambda syntax
 */
@Ignore
public class J04_FunctionalInterfacesTest {

	private final Random random = new Random();

	@Test
	public void testActionListenerLambda() {
		//given
		final Date dateMock = mock(Date.class);
		final ActionListener listener = null;

		//when
		listener.actionPerformed(null);

		//then
		verify(dateMock).setTime(1000L);
	}

	@Test
	public void testRunnableLambda() {
		//given
		final Date dateMock = mock(Date.class);
		Runnable block = null;

		//when
		block.run();

		//then
		verify(dateMock).setTime(1000L);
	}

	@Test
	public void testComparatorLambda() {
		final Comparator<String> strLenComparator = null;

		assertThat(strLenComparator.compare("abc", "def")).isZero();
		assertThat(strLenComparator.compare("abc", "defg")).isLessThan(0);
		assertThat(strLenComparator.compare("abc", "de")).isGreaterThan(0);
	}

	@Test
	public void testCustomFunctionalInterface() {
		final RandomSource source = null;

		Supplier<Integer> sourceSupplier = null;

		assertThat(source.oneOrMinusOne()).isIn(-1, 1);
		assertThat(sourceSupplier.get()).isIn(-1, 1);
	}

}

