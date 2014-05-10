package com.nurkiewicz.java8.interfaces;

public class XorEncrypter extends FunEncrypter {

	public XorEncrypter() {
		super(b -> (byte)(b ^ 0xCC));
	}
}
