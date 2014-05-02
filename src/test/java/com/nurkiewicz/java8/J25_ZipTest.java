package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.AbstractFuturesTest;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

@Ignore
public class J25_ZipTest extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(J25_ZipTest.class);

	@Test
	public void thenCombine() throws Exception {
		final CompletableFuture<String> java = questions("java");
		final CompletableFuture<String> scala = questions("scala");

		final CompletableFuture<Integer> both = java.
				thenCombine(scala, (String javaTitle, String scalaTitle) ->
						javaTitle.length() + scalaTitle.length()
				);

		both.thenAccept(length -> log.debug("Total length: {}", length));


		final CompletableFuture<Integer> f1 = null; //...
		final CompletableFuture<Integer> f2 = null; //...

		final CompletableFuture<Double> sum = f1.thenCombine(f2, (i1, i2) -> (i1 + i2 / 2.0));

		f1.acceptEither(f2, System.out::println);
	}

	@Test
	public void either() throws Exception {
		final CompletableFuture<String> java = questions("java");
		final CompletableFuture<String> scala = questions("scala");

		final CompletableFuture<String> both = java.
				applyToEither(scala, String::toUpperCase);

		both.thenAccept(title -> log.debug("First: {}", title));
	}


}

