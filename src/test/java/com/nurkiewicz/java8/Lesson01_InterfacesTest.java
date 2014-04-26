package com.nurkiewicz.java8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.nurkiewicz.java8.interfaces.Encrypter;
import com.nurkiewicz.java8.interfaces.ReverseEncrypter;
import com.nurkiewicz.java8.interfaces.RotEncrypter;
import com.nurkiewicz.java8.interfaces.XorEncrypter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;

/**
 * - Improve encrypter to use default methods
 */
@RunWith(Parameterized.class)
public class Lesson01_InterfacesTest {

	@Parameters
	public static Collection<Object[]> encrypterAndExpected() {
		Object[][] data = new Object[][]{
				{new ReverseEncrypter(), new byte[]{-91, -101, -111}},
				{new RotEncrypter(), new byte[]{103, 113, 123}},
				{new XorEncrypter(), new byte[]{-106, -88, -94}}
		};
		return Arrays.asList(data);
	}

	private final Encrypter encrypter;
	private final byte[] expected;

	public Lesson01_InterfacesTest(Encrypter encrypter, byte[] expected) {
		this.encrypter = encrypter;
		this.expected = expected;
	}

	@Test
	public void testAllMethods() throws IOException {
		final byte[] input = new byte[]{90, 100, 110};
		final char[] charInput = new char[]{'Z', 'd', 'n'};

		assertThat(encrypter.encode(input)).isEqualTo(expected);
		assertThat(encrypter.encode(new String(input), UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(charInput, UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(new StringReader(new String(charInput)), UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(new ByteArrayInputStream(input))).isEqualTo(expected);
	}

}
