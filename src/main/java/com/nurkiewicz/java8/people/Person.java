package com.nurkiewicz.java8.people;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Person {

	private final String name;
	private final Sex sex;
	private final int weight;
	private final int height;
	private final LocalDate dateOfBirth;
	private final Set<Phone> phoneNumbers;

	public Person(String name, Sex sex, int weight, int height, LocalDate dateOfBirth, Phone... phoneNumbers) {
		this.name = name;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumbers = new HashSet<>(Arrays.asList(phoneNumbers));
	}

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Sex getSex() {
		return sex;
	}

	public int getWeight() {
		return weight;
	}

	public Set<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}

	@Override
	public String toString() {
		return "Person{name='" + name + '\'' + ", sex=" + sex + ", weight=" + weight + ", height=" + height + ", dateOfBirth=" + dateOfBirth + '}';
	}
}
