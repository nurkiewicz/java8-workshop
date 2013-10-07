package pl.warsjawa.java8;

import org.junit.Test;
import pl.warsjawa.java8.people.Person;
import pl.warsjawa.java8.people.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - BufferedReader.lines()
 * - Comparator improvements
 */
public class Lesson05_FilesTest {

	private List<Person> loadPeopleDatabase() throws IOException {
		try (BufferedReader bufferedReader = open("/people.csv")) {
			return Collections.emptyList(); //bufferedReader.lines().
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
				Integer.parseInt(columns[2]),
				Integer.parseInt(columns[3]),
				LocalDate.of(
						Integer.parseInt(columns[6]),
						Integer.parseInt(columns[5]),
						Integer.parseInt(columns[4])
				)
		);
	}

	@Test
	public void shouldLoadAllPeople() throws IOException {
		final List<Person> people = loadPeopleDatabase();

		assertThat(people).hasSize(137);
	}

	@Test
	public void shouldSortByName() throws IOException {
		final List<Person> people = loadPeopleDatabase();

		final List<String> names = people.stream().
				map(Person::getName).
				distinct().
				collect(toList());

		assertThat(names).startsWith("Aleksandar", "Alexander", "Alexandra", "Ali", "Alice");
	}

	@Test
	public void shouldSortByDateOfBirthWhenSameNames() throws IOException {
		final List<Person> people = loadPeopleDatabase();

		final List<String> names = people.stream().
				map(p -> p.getName() + '-' + p.getDateOfBirth().getYear()).
				collect(toList());

		assertThat(names).startsWith("Aleksandar-1966", "Alexander-1986", "Alexander-1987", "Alexandra-1988", "Ali-1974");
	}

}
