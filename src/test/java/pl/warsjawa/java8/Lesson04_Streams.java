package pl.warsjawa.java8;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static pl.warsjawa.java8.Sex.FEMALE;
import static pl.warsjawa.java8.Sex.MALE;

/**
 * - More complex operations on Stream, (what is Stream)? (map, filter, forEach, sorted)
 * - No collectors yet
 */
public class Lesson04_Streams {

	public static final List<Person> PEOPLE = Arrays.asList(
			new Person("Alice", FEMALE, 54, 178, LocalDate.of(1984, Month.AUGUST, 17)),
			new Person("Bob", MALE, 71, 183, LocalDate.of(1982, 2, 5)),
			new Person("Eve", FEMALE, 61, 176, LocalDate.of(1987, Month.FEBRUARY, 9)),
			new Person("Jane", FEMALE, 62, 169, LocalDate.of(1986, Month.DECEMBER, 21)),
			new Person("Steve", MALE, 86, 191, LocalDate.of(1980, Month.MAY, 4))
	);

}
