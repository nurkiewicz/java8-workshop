package com.nurkiewicz.java8.interfaces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

public interface Encrypter {
	byte[] encode(byte[] bytes);

	default byte[] encode(String str, Charset charset) {
		return encode(str.getBytes(charset));
	}

	default byte[] encode(char[] chars, Charset charset) {
		return encode(String.valueOf(chars), charset);
	}

	default byte[] encode(Reader reader, Charset charset) throws IOException {
		return encode(IOUtils.toByteArray(reader, charset));
	}

	default byte[] encode(InputStream is) throws IOException {
		return encode(IOUtils.toByteArray(is));
	}
}
