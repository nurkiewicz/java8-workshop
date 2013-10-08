package pl.warsjawa.java8.people;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PersonDao {

	public List<Person> loadPeopleDatabase() throws IOException {
		try (BufferedReader bufferedReader = open("/people.csv")) {
			return bufferedReader.lines().
					filter(line -> !line.startsWith("#")).
					map(this::parseLine).
					filter(p -> !p.getName().isEmpty()).
					sorted(Comparator.comparing(Person::getName).thenComparing(Person::getDateOfBirth)).
					collect(toList());
		}
	}

	private BufferedReader open(String fileName) {
		return new BufferedReader(
				new InputStreamReader(
						getClass().getResourceAsStream(fileName),
						StandardCharsets.UTF_8));
	}

	private Person parseLine(String line) {
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