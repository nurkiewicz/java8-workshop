package pl.warsjawa.java8;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Date;
import java.util.function.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * - Use explicit Function, Predicate, Supplier, Consuner (like Guava)
 * - Change Encrypter to class taking Function<Byte, Byte>
 * - Turning Function, Supplier and Producer into lambda
 * - Method references (method, static method, constructor)
 */
@Ignore
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

		final InOrder order = inOrder(dateMock);
		order.verify(dateMock).setTime(1000L);
		order.verify(dateMock).setTime(2000L);
	}

	@Test
	public void shouldCallOtherClassInPrimitiveConsumer() {
		final Date dateMock = mock(Date.class);

		final LongConsumer consumer = null;

		consumer.accept(1000L);
		consumer.accept(2000L);

		final InOrder order = inOrder(dateMock);
		order.verify(dateMock).setTime(1000L);
		order.verify(dateMock).setTime(2000L);
	}


}
