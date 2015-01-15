package com.nurkiewicz.java8;

import com.nurkiewicz.java8.interfaces.Encrypter;
import com.nurkiewicz.java8.interfaces.ReverseEncrypter;
import com.nurkiewicz.java8.interfaces.RotEncrypter;
import com.nurkiewicz.java8.interfaces.XorEncrypter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - Improve encrypter to use default methods to avoid code duplication.
 */
@RunWith(JUnitParamsRunner.class)
public class J02_InterfacesTest {

	@Test
	@Parameters
	public void testAllMethods(Encrypter encrypter, byte[] expected) throws IOException {
		final byte[] input = new byte[]{90, 100, 110};
		final char[] charInput = new char[]{'Z', 'd', 'n'};

		assertThat(encrypter.encode(input)).isEqualTo(expected);
		assertThat(encrypter.encode(new String(input), UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(charInput, UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(new StringReader(new String(charInput)), UTF_8)).isEqualTo(expected);
		assertThat(encrypter.encode(new ByteArrayInputStream(input))).isEqualTo(expected);
	}

	private Object[] parametersForTestAllMethods() {
		return $(
				$(new ReverseEncrypter(), new byte[]{-91, -101, -111}),
				$(new RotEncrypter(), new byte[]{103, 113, 123}),
				$(new XorEncrypter(), new byte[]{-106, -88, -94})
		);
	}
}
