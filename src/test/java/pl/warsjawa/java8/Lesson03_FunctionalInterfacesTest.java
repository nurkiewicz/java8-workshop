package pl.warsjawa.java8;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - What is a functional interface? @FunctionalInterface
 * - Using lambdas instead of plain old Java classes (JButton)
 */
public class Lesson03_FunctionalInterfacesTest {

	private final Random random = new Random();

	@Test
	public void testActionListenerLambda() {
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getModifiers());
				System.out.println(e.getActionCommand());
			}
		});
	}

	@Test
	public void testRunnableLambda() {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				System.out.println("Runnable!");
			}
		};
	}

	@Test
	public void testCustomFunctionalInterface() {
		final RandomSource source = new RandomSource() {
			@Override
			public int oneOrMinusOne() {
				return random.nextInt(2) * 2 - 1;
			}
		};

		Supplier<Integer> sourceSupplier = null;

		assertThat(source.oneOrMinusOne()).isIn(-1, 1);
		assertThat(sourceSupplier.get()).isIn(-1, 1);
	}

}

@FunctionalInterface
interface RandomSource {

	int oneOrMinusOne();

//	int oneTwoOrThree();

}
