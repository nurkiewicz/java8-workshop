package com.nurkiewicz.java8;

import com.nurkiewicz.java8.people.Person;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static com.nurkiewicz.java8.people.Sex.MALE;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Transform old-fashioned code using nulls with Optional
 * Hint: use map/filter/flatMap/ifPresent
 */
public class J06_OptionalTest {

	private Person findPersonOrNull(int id) {
		if (id > 0) {
			return new Person("James", MALE, 62, 169, LocalDate.of(1970 + id, Month.DECEMBER, 21));
		} else {
			return null;
		}
	}

	private String lookupAddressOrNull(Person person) {
		if (person.getDateOfBirth().isAfter(LocalDate.of(1993, Month.JANUARY, 1))) {
			return "Some St.  ";
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

	/**
	 * Don't change, call from {@link #tryLookupAddressById(int)}
	 */
	private Optional<Person> tryFindPerson(int id) {
		return Optional.ofNullable(findPersonOrNull(id));
	}

	/**
	 * Don't change, call from {@link #tryLookupAddressById(int)}
	 */
	private Optional<String> tryLookupAddress(Person person) {
		return Optional.ofNullable(lookupAddressOrNull(person));
	}

	/**
	 * TODO: Copy and refactor code from {@link #lookupAddressByIdOrNull}, but avoid nulls
	 */
	private Optional<String> tryLookupAddressById(int id) {
		return tryFindPerson(id)
				.filter(person -> person.getSex() == MALE)
				.flatMap(this::tryLookupAddress)
				.filter(s -> !s.isEmpty())
				.map(String::trim);
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
