package com.nurkiewicz.java8.interfaces;

public class RotEncrypter extends FunEncrypter {

	public RotEncrypter() {
		super(b -> (byte)(b + 13));
	}
}
