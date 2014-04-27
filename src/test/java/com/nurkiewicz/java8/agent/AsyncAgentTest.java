package com.nurkiewicz.java8.agent;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@Ignore
public class AsyncAgentTest {

	@Test
	public void newAgentShouldHaveInitialValue() {
		//given
		final Agent<Integer> agent = Agent.create(42);

		//when
		final int actual = agent.get();

		//then
		assertThat(actual).isEqualTo(42);
	}

	@Test
	public void shouldApplyTwoChanges() {
		//given
		final Agent<BigInteger> agent = Agent.create(BigInteger.ONE);

		//when
		agent.send(x -> x.add(BigInteger.ONE));
		agent.send(x -> x.add(BigInteger.TEN));

		//then
		await().until(() -> agent.get().equals(BigInteger.valueOf(1 + 1 + 10)));
	}

	@Test
	public void shouldApplyChangesFromOneThreadInOrder() {
		//given
		final Agent<String> agent = Agent.create("");

		//when
		agent.send(s -> s + "A");
		agent.send(s -> s + "B");
		agent.send(s -> s + "C");
		agent.send(String::toLowerCase);
		agent.send(s -> "D" + s);

		//then
		await().untilCall(to(agent).get(), is(4));
		assertThat(agent.get()).isEqualTo("Dabc");
	}

	@Test
	public void shouldRunInDifferentThread() {
		//given
		final long mainThreadId = Thread.currentThread().getId();
		final Agent<Long> agent = Agent.create(mainThreadId);

		//when
		agent.send(x -> Thread.currentThread().getId());

		//then
		await().untilCall(to(agent).get(), is(mainThreadId));
	}

	@Test
	public void aLotOfAgents() {
		//given
		final int totalAgents = 1_000_000;
		final List<Agent<String>> agents = IntStream
				.range(0, totalAgents)
				.boxed()
				.map(i -> Agent.create(""))
				.collect(toList());

		//when
		agents.forEach(a -> {
			a.send(s -> s + "a");
			a.send(s -> s + "b");
			a.send(s -> s + "c");
		});

		//then
		agents.forEach(a ->
				await().until(
						() -> a.get().equals("abc")));
	}

}