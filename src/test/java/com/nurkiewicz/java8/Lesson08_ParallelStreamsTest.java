package com.nurkiewicz.java8;

import org.junit.Test;
import com.nurkiewicz.java8.people.PersonDao;

import java.io.IOException;

/**
 * - run time difference
 * - unspecified order
 * - commonPool()
 */
public class Lesson08_ParallelStreamsTest {

	private final PersonDao dao = new PersonDao();

	@Test
	public void parallel() throws IOException {
		dao.loadPeopleDatabase().parallelStream();
	}


}


