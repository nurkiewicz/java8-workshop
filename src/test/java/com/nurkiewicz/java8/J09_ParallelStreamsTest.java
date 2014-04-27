package com.nurkiewicz.java8;

import com.nurkiewicz.java8.people.PersonDao;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * - run time difference
 * - unspecified order
 * - commonPool()
 */
@Ignore
public class J09_ParallelStreamsTest {

	private final PersonDao dao = new PersonDao();

	@Test
	public void parallel() throws IOException {
		dao.loadPeopleDatabase().parallelStream();
	}


}


