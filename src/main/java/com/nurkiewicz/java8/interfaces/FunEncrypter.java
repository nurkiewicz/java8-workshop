package com.nurkiewicz.java8.interfaces;

import java.util.function.Function;

public class FunEncrypter implements Encrypter {

	private final Function<Byte, Byte> encFun;

	public FunEncrypter(Function<Byte, Byte> encFun) {
		this.encFun = encFun;
	}

	@Override
	public byte[] encode(byte[] bytes) {
		final byte[] result = new byte[bytes.length];
		for (int i = 0; i < bytes.length; ++i) {
			result[i] = encFun.apply(bytes[i]);
		}
		return result;
	}
}
