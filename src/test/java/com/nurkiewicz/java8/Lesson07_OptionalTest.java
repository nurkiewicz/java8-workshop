package com.nurkiewicz.java8;

import org.junit.Ignore;
import org.junit.Test;
import com.nurkiewicz.java8.people.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;
import static com.nurkiewicz.java8.people.Sex.MALE;

/**
 * map/filter/flatMap/ifPresent
 */
@Ignore
public class Lesson07_OptionalTest {

	private Person findPersonOrNull(int id) {
		if (id > 0) {
			return new Person("James", MALE, 62, 169, LocalDate.of(1970 + id, Month.DECEMBER, 21));
		} else {
			return null;
		}
	}

	private String lookupAddressOrNull(Person person) {
		if (person.getDateOfBirth().isAfter(LocalDate.of(1993, Month.JANUARY, 1))) {
			return "Some St.";
		} else {
			return null;
		}
	}

	private String lookupAddressByIdOrNull(int id) {
		final Person personOrNull = findPersonOrNull(id);
		if (personOrNull != null) {
			if (personOrNull.getSex() == MALE) {
				final String addressOrNull = lookupAddressOrNull(personOrNull);
				if (addressOrNull != null && !addressOrNull.isEmpty()) {
					return addressOrNull.trim();
				} else {
					return null;
				}
			}
		}
		return null;
	}

	private Optional<Person> tryFindPerson(int id) {
		return Optional.ofNullable(findPersonOrNull(id));
	}

	private Optional<String> tryLookupAddress(Person person) {
		return Optional.ofNullable(lookupAddressOrNull(person));
	}

	private Optional<String> tryLookupAddressById(int id) {
		return Optional.empty(); // tryFindPerson(id).
	}

	@Test
	public void nulls() {
		assertThat(lookupAddressByIdOrNull(-1)).isNull();
		assertThat(lookupAddressByIdOrNull(21)).isNull();
		assertThat(lookupAddressByIdOrNull(41)).isEqualTo("Some St.");
	}

	@Test
	public void optionals() {
		assertThat(tryLookupAddressById(-1).isPresent()).isFalse();
		assertThat(tryLookupAddressById(21).isPresent()).isFalse();
		assertThat(tryLookupAddressById(41).get()).isEqualTo("Some St.");
	}

}
