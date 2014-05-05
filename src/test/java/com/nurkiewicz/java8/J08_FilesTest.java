package com.nurkiewicz.java8;

import com.nurkiewicz.java8.people.Person;
import com.nurkiewicz.java8.people.PersonDao;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - BufferedReader.lines()
 * - Comparator improvements
 */
@Ignore
public class J08_FilesTest {

	private final PersonDao dao = new PersonDao();

	@Test
	public void shouldLoadAllPeople() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		assertThat(people).hasSize(137);
	}

	@Test
	public void shouldSortByName() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final List<String> names = people.stream().
				map(Person::getName).
				distinct().
				collect(toList());

		assertThat(names).startsWith("Aleksandar", "Alexander", "Alexandra", "Ali", "Alice");
	}

	/**
	 * Hint: Comparators.comparing()
	 */
	@Test
	public void shouldSortFemalesByHeightDesc() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final List<String> names = people.stream()
				.map(Person::getName)
				.collect(toList());

		assertThat(names).startsWith("Mia", "Sevinj", "Anna", "Sofia");
	}

	@Test
	public void shouldSortByDateOfBirthWhenSameNames() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final List<String> names = people.stream().
				map(p -> p.getName() + '-' + p.getDateOfBirth().getYear()).
				collect(toList());

		assertThat(names).startsWith("Aleksandar-1966", "Alexander-1986", "Alexander-1987", "Alexandra-1988", "Ali-1974");
	}

	/**
	 * @see Files#list(Path)
	 * @throws Exception
	 */
	@Test
	public void shouldGenerateStreamOfAllFilesIncludingSubdirectoriesRecursively() throws Exception {
		//given
		final String fileToSearch = J08_FilesTest.class.getSimpleName() + ".java";

		//when
		final Optional<Path> found = filesInDir(Paths.get("."))
				.filter(path -> path.endsWith(fileToSearch))
				.findAny();

		//then
		assertThat(found.isPresent()).isTrue();
	}

	private static Stream<Path> filesInDir(Path dir) {
		throw new UnsupportedOperationException("filesInDir()");
	}

}
