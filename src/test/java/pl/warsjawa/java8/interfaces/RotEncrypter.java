package pl.warsjawa.java8.interfaces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Tomasz Nurkiewicz
 * @since 02.10.13, 21:54
 */
class RotEncrypter implements Encrypter {

	@Override
	public byte[] encode(byte[] bytes) {
		final byte[] result = new byte[bytes.length];
		for (int i = 0; i < bytes.length; ++i) {
			result[i] = (byte)(bytes[i] + 13);
		}
		return result;
	}

	@Override
	public byte[] encode(String str, Charset charset) {
		return encode(str.getBytes(charset));
	}

	@Override
	public byte[] encode(char[] chars, Charset charset) {
		return encode(String.valueOf(chars), charset);
	}

	@Override
	public byte[] encode(Reader reader, Charset charset) throws IOException {
		return encode(IOUtils.toByteArray(reader, charset));
	}

	@Override
	public byte[] encode(InputStream is) throws IOException {
		return encode(IOUtils.toByteArray(is));
	}
}
