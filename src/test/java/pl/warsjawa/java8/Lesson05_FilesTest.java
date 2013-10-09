package pl.warsjawa.java8;

import org.junit.Ignore;
import org.junit.Test;
import pl.warsjawa.java8.people.Person;
import pl.warsjawa.java8.people.PersonDao;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - BufferedReader.lines()
 * - Comparator improvements
 */
@Ignore
public class Lesson05_FilesTest {

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

	@Test
	public void shouldSortByDateOfBirthWhenSameNames() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final List<String> names = people.stream().
				map(p -> p.getName() + '-' + p.getDateOfBirth().getYear()).
				collect(toList());

		assertThat(names).startsWith("Aleksandar-1966", "Alexander-1986", "Alexander-1987", "Alexandra-1988", "Ali-1974");
	}

}
