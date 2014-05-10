package com.nurkiewicz.java8.interfaces;

public class ReverseEncrypter extends FunEncrypter {

	public ReverseEncrypter() {
		super(b -> (byte)(0xFF - b));
	}
}
