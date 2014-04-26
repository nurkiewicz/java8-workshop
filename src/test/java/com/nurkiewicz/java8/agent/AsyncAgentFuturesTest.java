package com.nurkiewicz.java8.agent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.fest.assertions.api.Assertions.assertThat;

public class AsyncAgentFuturesTest {

	@Test
	public void shouldCompleteFutureWhenSendDone() throws ExecutionException, InterruptedException {
		//given
		final Agent<Integer> agent = Agent.create(1);

		//when
		final CompletableFuture<Integer> future = agent.sendAndGet(x -> x + 2);

		//then
		assertThat(future.get()).isEqualTo(1 + 2);
	}

	@Test
	public void shouldCompleteWhenAllPendingFuturesAreDone() throws ExecutionException, InterruptedException {
		//given
		final Agent<String> agent = Agent.create("");

		//when
		agent.send(s -> s + "1");
		agent.send(s -> s + "2");
		final CompletableFuture<String> future = agent.sendAndGet(s -> s + "3");

		//then
		assertThat(future.get()).isEqualTo("123");
	}

	@Test
	public void shouldWaitForTwoAgents() throws ExecutionException, InterruptedException {
		//given
		final Agent<String> agentOne = Agent.create("");
		final Agent<String> agentTwo = Agent.create("");

		//when
		final CompletableFuture<String> futureOne = agentOne.sendAndGet(s -> s + "One");
		final CompletableFuture<String> futureTwo = agentTwo.sendAndGet(s -> s + "Two");

		//then
		CompletableFuture<String> both = null;
		assertThat(both.get()).isEqualTo("OneTwo");
	}

	@Test
	public void shouldReflectAllPriorChangesWhenAsyncGet() throws ExecutionException, InterruptedException {
		//given
		final Agent<Integer> agent = Agent.create(1);

		//when
		agent.send(x -> x + 2);
		final CompletableFuture<Integer> future = agent.getAsync();

		//then
		assertThat(future.get()).isEqualTo(1 + 2);
	}

	@Test
	public void shouldNotSeeChangesMadeAfterAsyncGet() throws ExecutionException, InterruptedException {
		//given
		final Agent<Integer> agent = Agent.create(1);

		//when
		agent.send(x -> x + 2);
		final CompletableFuture<Integer> future = agent.getAsync();
		agent.send(x -> x + 3);

		//then
		assertThat(future.get()).isEqualTo(1 + 2);
	}

	@Test
	public void shouldCompleteWhenConditionIsMet() throws ExecutionException, InterruptedException {
		//given
		final Agent<String> agent = Agent.create("");

		//when
		final CompletableFuture<String> future = agent.completeIf(s -> s.length() > 2);
		agent.send(s -> s + "1");
		agent.send(s -> s + "2");
		agent.send(s -> s + "3");

		//then
		assertThat(future.get()).isEqualTo("12");
	}

	@Test
	public void shouldCompleteImmediatelyIfConditionAlreadyMet() throws ExecutionException, InterruptedException {
		//given
		final Agent<String> agent = Agent.create("");

		//when
		final CompletableFuture<String> future = agent.completeIf(String::isEmpty);
		agent.send(s -> s + "1");

		//then
		assertThat(future.get()).isEmpty();
	}

}