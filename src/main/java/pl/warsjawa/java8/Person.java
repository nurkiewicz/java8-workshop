package pl.warsjawa.java8;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Tomasz Nurkiewicz
 * @since 28.09.13, 23:42
 */
public class Person {

	private final String name;
	private final Sex sex;
	private final int weight;
	private final int height;
	private final LocalDate dateOfBirth;

	public Person(String name, Sex sex, int weight, int height, LocalDate dateOfBirth) {
		this.name = name;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.dateOfBirth = dateOfBirth;
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

	@Override
	public String toString() {
		return "Person{name='" + name + '\'' + ", sex=" + sex + ", weight=" + weight + ", height=" + height + ", dateOfBirth=" + dateOfBirth + '}';
	}
}
