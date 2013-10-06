package pl.warsjawa.java8;

import org.junit.Test;

import java.util.Date;
import java.util.function.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * - Use explicit Function, Predicate, Supplier, Consuner (like Guava)
 * - Replace functional interface with lambda
 * - Method references
 */
public class Lesson02_FunctionTest {

	@Test
	public void shouldPrependHello() {
		final Function<Integer, String> fun = null;

		assertThat(fun.apply(42)).isEqualTo("Answer is 42");
	}

	@Test
	public void shouldProduceAnser() {
		final Supplier<Integer> answerFun = null;

		assertThat(answerFun.get()).isEqualTo(42);
	}

	@Test
	public void shouldDecideIfNegative() {
		final Predicate<Double> isNegative = null;

		assertThat(isNegative.test(3.0)).isFalse();
		assertThat(isNegative.test(0.0)).isFalse();
		assertThat(isNegative.test(-1.1)).isTrue();
	}

	@Test
	public void shouldCallOtherClassInConsumer() {
		final Date dateMock = mock(Date.class);

		final Consumer<Long> consumer = null;

		consumer.accept(1000L);
		consumer.accept(2000L);
	}

	@Test
	public void shouldCallOtherClassInPrimitiveConsumer() {
		final Date dateMock = mock(Date.class);

		final LongConsumer consumer = null;

		consumer.accept(1000L);
		consumer.accept(2000L);
	}


}
