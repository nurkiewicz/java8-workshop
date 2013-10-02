package pl.warsjawa.java8.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * @author Tomasz Nurkiewicz
 * @since 02.10.13, 21:54
 */
interface Encrypter {
	byte[] encode(byte[] bytes);

	byte[] encode(String str, Charset charset);

	byte[] encode(char[] chars, Charset charset);

	byte[] encode(Reader reader, Charset charset) throws IOException;

	byte[] encode(InputStream is) throws IOException;
}
