package com.nurkiewicz.java8.people;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonService implements AutoCloseable {

	private final ExecutorService pool = Executors.newFixedThreadPool(8);
	private final PersonDao dao = new PersonDao();

	public CompletableFuture<List<Person>> loadPeople() {
		return CompletableFuture.supplyAsync(dao::loadPeopleDatabase, pool);
	}

	public CompletableFuture<Person> loadPerson(String name) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public CompletableFuture<Double> calculateRisk(Person person) {
		return CompletableFuture.supplyAsync(() -> person.getHeight() / 200.0, pool);
	}

	public double bodyMassIndex(Person person) {
		return person.getWeight() / (person.getHeight() / 100.0 * person.getHeight() / 100.0);
	}

	public CompletableFuture<Integer> nextUniqueId() {
		return CompletableFuture.supplyAsync(() -> 42, pool);
	}


	@Override
	public void close() throws Exception {
		pool.shutdownNow();
	}

}
