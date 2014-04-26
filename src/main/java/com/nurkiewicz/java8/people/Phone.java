package com.nurkiewicz.java8.people;

public class Phone {

	private final int countryCode;
	private final long number;

	public Phone(int countryCode, long number) {
		this.countryCode = countryCode;
		this.number = number;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public long getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Phone{" + countryCode + " " + number + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Phone phone = (Phone) o;
		return countryCode == phone.countryCode && number == phone.number;

	}

	@Override
	public int hashCode() {
		int result = countryCode;
		result = 31 * result + (int) (number ^ (number >>> 32));
		return result;
	}
}
