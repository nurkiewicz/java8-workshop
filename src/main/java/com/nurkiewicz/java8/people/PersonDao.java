package com.nurkiewicz.java8.people;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

public class PersonDao {

	public List<Person> loadPeopleDatabase() {
		try (BufferedReader bufferedReader = open("/people.csv")) {
			throw new UnsupportedOperationException("loadPeopleDatabase()");
			// return bufferedReader.lines().
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BufferedReader open(String fileName) {
		return new BufferedReader(
				new InputStreamReader(
						getClass().getResourceAsStream(fileName),
						StandardCharsets.UTF_8));
	}

	private Person parsePerson(String line) {
		final String[] columns = line.split(",");
		return new Person(
				columns[0],
				Sex.valueOf(columns[1]),
				Integer.parseInt(columns[3]),
				Integer.parseInt(columns[2]),
				LocalDate.of(
						Integer.parseInt(columns[6]),
						Integer.parseInt(columns[5]),
						Integer.parseInt(columns[4])
				)
		);
	}


}