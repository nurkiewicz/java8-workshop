package com.nurkiewicz.java8;

import com.nurkiewicz.java8.people.Person;
import com.nurkiewicz.java8.people.PersonDao;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - run time difference
 * - unspecified order
 * - commonPool()
 */
public class J09_ParallelStreamsTest {

	private final PersonDao dao = new PersonDao();

	@Test
	public void parallel() throws IOException {
		final List<Person> veryTallParallel = dao.loadPeopleDatabase()
				.parallelStream()
				.filter(p -> p.getHeight() > 190)
				.collect(toList());
		final List<Person> veryTall = dao.loadPeopleDatabase()
				.stream()
				.filter(p -> p.getHeight() > 190)
				.collect(toList());

		assertThat(veryTallParallel).isEqualTo(veryTall);
	}


}


