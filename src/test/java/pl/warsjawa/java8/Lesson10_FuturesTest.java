package pl.warsjawa.java8;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import pl.warsjawa.java8.people.Person;
import pl.warsjawa.java8.people.PersonService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

/**
 * - supplyAync
 * - thenApply
 * - thenAccept/thenRun
 * - thenCompose (chaining futures)
 * - thenCombine - waiting for two
 * - applyToEither/acceptEither - transform/wait for first
 * - allOf/anyOf
 */
@Ignore
public class Lesson10_FuturesTest {

	public static final String VIKTORIA = "Viktoria";
	public static final String WILLIAM = "William";
	private final PersonService personService = new PersonService();

	@After
	public void close() throws Exception {
		personService.close();
	}

	@Test
	public void countPeople() throws ExecutionException, InterruptedException {
		final CompletableFuture<List<Person>> people = personService.loadPeople();

		assertThat(people.get()).hasSize(137);
	}

	@Test
	public void findByName() throws ExecutionException, InterruptedException {
		final CompletableFuture<Person> future = personService.loadPerson(VIKTORIA);

		assertThat(future.get().getName()).isEqualTo(VIKTORIA);
	}

	@Test
	public void calculateRiskOfViktoria() throws ExecutionException, InterruptedException {
		final CompletableFuture<Double> risk = null; // personService.loadPerson(VIKTORIA)

		assertThat(risk.get()).isEqualTo(0.87, offset(0.1));
	}

	@Test
	public void averageRiskOfViktoriaAndWilliam() throws ExecutionException, InterruptedException {
		final CompletableFuture<Double> riskOfViktoria = null; // personService.loadPerson(VIKTORIA)
		final CompletableFuture<Double> riskOfWilliam = null; // personService.loadPerson(WILLIAM)

		final CompletableFuture<Double> averageRiskOfBoth = riskOfViktoria.thenCombine(riskOfWilliam, (r1, r2) -> (r1 + r2) / 2);

		assertThat(averageRiskOfBoth.get()).isEqualTo(0.89, offset(0.1));
	}

	@Test
	public void findPersonWithHighestBmi() throws ExecutionException, InterruptedException {
		final CompletableFuture<Person> mostUnhealthy = null; // personService.loadPeople().;

		assertThat(mostUnhealthy.get().getName()).isEqualTo("Sevinj");
	}

}
