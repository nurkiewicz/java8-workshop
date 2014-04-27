package com.nurkiewicz.java8;

class Person {



	private final int age;
	private final String name;

	Person(int age, String name) {
		this.age = age;
		this.name = name;

		boolean x = false;
		if(x || !x) {}

	}

	public int getAge() {
		return age;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		if (this == o) return true;


		return age == person.age && name.equals(person.name);

	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int result = age;
		result = 31 * result + name.hashCode();
		return result;
	}



}

