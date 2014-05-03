package com.nurkiewicz.rxjava;

import com.nurkiewicz.java8.FutureOps;
import org.junit.Ignore;
import org.junit.Test;
import rx.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.hamcrest.core.Is.is;

@Ignore
public class R48_CompletableFutureAndObservable {

	@Test
	public void shouldWrapAlreadyFinishedFutureIntoObservable() throws Exception {
		//given
		final CompletableFuture<String> future = CompletableFuture.completedFuture("Foo");

		//when
		final Observable<String> observable = ObservableOps.toObservable(future);

		//then
		final Iterable<String> resultOfFuture = observable.toBlockingObservable().toIterable();
		assertThat(resultOfFuture).containsExactly("Foo");
	}

	@Test
	public void shouldPropagateFutureExceptionIntoObservable() throws Exception {
		//given
		final CompletableFuture<String> future = FutureOps.failed(new UnsupportedOperationException("Don't panic!"));

		//when
		final Observable<String> observable = ObservableOps.toObservable(future);

		//then
		AtomicBoolean flag = new AtomicBoolean();
		observable.doOnError(e -> flag.set(true));
		await().untilAtomic(flag, is(true));
	}

	@Test
	public void shouldTransformEmptyObservableToFutureWithEmptyList() throws Exception {
		//given
		final Observable<String> observable = Observable.empty();

		//when
		final CompletableFuture<List<String>> future = ObservableOps.toCompletableFuture(observable);

		//then
		assertThat(future.get(1, SECONDS)).isEmpty();
	}

	@Test
	public void shouldTransformObservableWithFewItemsToList() throws Exception {
		//given
		final Observable<String> observable = Observable.from("A", "B");

		//when
		final CompletableFuture<List<String>> future = ObservableOps.toCompletableFuture(observable);

		//then
		assertThat(future.get(1, SECONDS)).containsExactly("A", "B");
	}

	@Test
	public void shouldPropagateErrorFromObservable() throws Exception {
		//given
		final Observable<String> observable = Observable.error(new UnsupportedOperationException("Don't panic!"));

		//when
		final CompletableFuture<List<String>> future = ObservableOps.toCompletableFuture(observable);

		//then
		try {
			future.get(1, SECONDS);
			failBecauseExceptionWasNotThrown(UnsupportedOperationException.class);
		} catch (InterruptedException e) {
			assertThat(e.getCause()).hasMessageContaining("panic");
		}
	}

}
