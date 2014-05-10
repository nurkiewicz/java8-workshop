package com.nurkiewicz.java8;

import com.nurkiewicz.java8.agent.Agent;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.fest.assertions.api.Assertions.assertThat;

public class J29_AsyncAgentFuturesTest {

	private ExecutorService pool = Executors.newFixedThreadPool(100);

	@After
	public void stopPool() {
		pool.shutdown();
	}

	@Test
	public void shouldCompleteFutureWhenSendDone() throws ExecutionException, InterruptedException {
		//given
		final Agent<Integer> agent = Agent.create(1, pool);

		//when
		final CompletableFuture<Integer> future = agent.sendAndGet(x -> x + 2);

		//then
		assertThat(future.get()).isEqualTo(1 + 2);
	}

	@Test
	public void shouldCompleteWhenAllPendingFuturesAreDone() throws ExecutionException, InterruptedException {
		//given
		final Agent<String> agent = Agent.create("", pool);

		//when
		agent.send(s -> s + "1");
		agent.send(s -> s + "2");
		final CompletableFuture<String> future = agent.sendAndGet(s -> s + "3");

		//then
		assertThat(future.get()).isEqualTo("123");
	}

	@Test
	public void shouldWaitUntilConditionIsMetOnTwoAgents() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<String> agentOne = Agent.create("Abc", pool);
		final Agent<String> agentTwo = Agent.create("Def", pool);

		//when
		final CompletableFuture<String> futureOne = agentOne.completeIf(String::isEmpty);
		final CompletableFuture<String> futureTwo = agentTwo.completeIf(String::isEmpty);
		agentOne.send(s -> "");
		agentTwo.send(s -> "");

		//then
		futureOne.get(1, TimeUnit.SECONDS);
		futureTwo.get(1, TimeUnit.SECONDS);
		assertThat(agentOne.get()).isEmpty();
		assertThat(agentTwo.get()).isEmpty();
	}

	@Test
	public void shouldWaitForTwoAgents() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<String> agentOne = Agent.create("", pool);
		final Agent<String> agentTwo = Agent.create("", pool);

		//when
		final CompletableFuture<String> futureOne = agentOne.sendAndGet(s -> s + "One");
		final CompletableFuture<String> futureTwo = agentTwo.sendAndGet(s -> s + "Two");

		//then
		CompletableFuture<String> both = new CompletableFuture<>();
		futureOne.thenAcceptBoth(futureTwo, (one, two) -> both.complete(one + two));
		assertThat(both.get(1, TimeUnit.SECONDS)).isEqualTo("OneTwo");
	}

	@Test
	public void shouldReflectAllPriorChangesWhenAsyncGet() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<Integer> agent = Agent.create(1, pool);

		//when
		agent.send(x -> x + 2);
		final CompletableFuture<Integer> future = agent.getAsync();

		//then
		assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(1 + 2);
	}

	@Test
	public void shouldNotSeeChangesMadeAfterAsyncGet() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<Integer> agent = Agent.create(1, pool);

		//when
		agent.send(x -> x + 2);
		final CompletableFuture<Integer> future = agent.getAsync();
		agent.send(x -> x + 3);

		//then
		assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(1 + 2);
	}

	@Test
	public void shouldCompleteWhenConditionIsMet() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<String> agent = Agent.create("", pool);

		//when
		final CompletableFuture<String> future = agent.completeIf(s -> s.length() >= 2);
		agent.send(s -> s + "1");
		agent.send(s -> s + "2");
		agent.send(s -> s + "3");

		//then
		assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo("12");
	}

	@Test
	public void shouldCompleteImmediatelyIfConditionAlreadyMet() throws ExecutionException, InterruptedException, TimeoutException {
		//given
		final Agent<String> agent = Agent.create("", pool);

		//when
		final CompletableFuture<String> future = agent.completeIf(String::isEmpty);
		agent.send(s -> s + "1");

		//then
		assertThat(future.get(1, TimeUnit.SECONDS)).isEmpty();
	}

}