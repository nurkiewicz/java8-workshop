package com.nurkiewicz.java8;

import com.nurkiewicz.java8.people.Person;
import com.nurkiewicz.java8.people.PersonDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * - run time difference
 * - unspecified order
 * - commonPool()
 */
public class J10_ParallelStreamsTest {

	private final PersonDao dao = new PersonDao();
	private ForkJoinPool pool;

	@Before
	public void setupPool() {
		pool = new ForkJoinPool(8);
	}

	@After
	public void shutdownPool() {
		pool.shutdownNow();
	}

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

	/**
	 * Doesn't test much, just a demo.
	 */
	@Test
	public void runningInCustomForkJoinPool() throws IOException, ExecutionException, InterruptedException {
		List<Integer> ints = IntStream
				.range(0, 20)
				.mapToObj(Integer::valueOf)
				.collect(toList());

		pool.submit(() -> {
			ints.parallelStream().forEach(i ->
					System.out.println(Thread.currentThread().getName() + ": " + i));
		}).get();
	}

	@Test
	public void parallelFilterKeepsOrder() throws IOException, ExecutionException, InterruptedException {
		List<Integer> ints = IntStream
				.range(0, 20)
				.mapToObj(Integer::valueOf)
				.collect(toList());

		ForkJoinTask<List<Integer>> future = pool.submit(() -> {
			return ints
					.parallelStream()
					.filter(i -> i % 6 == 0)
					.collect(toList());
		});
		assertThat(future.get()).containsExactly(0, 6, 12, 18);
	}


}


