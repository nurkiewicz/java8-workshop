package com.nurkiewicz.java8;

import com.nurkiewicz.java8.util.AbstractFuturesTest;
import com.nurkiewicz.rxjava.stackoverflow.Question;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Ignore
public class J24_FlatMapTest extends AbstractFuturesTest {

	private static final Logger log = LoggerFactory.getLogger(J24_FlatMapTest.class);

	private CompletableFuture<Document> javaQuestions() {
		return CompletableFuture.supplyAsync(() ->
				client.mostRecentQuestionsAbout("java"),
				executorService);
	}

	private CompletableFuture<Question> findMostInterestingQuestion(Document document) {
		return CompletableFuture.completedFuture(new Question());
	}

	private CompletableFuture<String> googleAnswer(Question q) {
		return CompletableFuture.completedFuture("42");
	}

	private CompletableFuture<Integer> postAnswer(String answer) {
		return CompletableFuture.completedFuture(200);
	}

	@Test
	public void thenApplyIsWrong() throws Exception {
		final CompletableFuture<CompletableFuture<Question>> future =
				javaQuestions().thenApply(doc -> findMostInterestingQuestion(doc));
	}

	@Test
	public void thenAcceptIsPoor() throws Exception {
		javaQuestions().thenAccept(document -> {
			findMostInterestingQuestion(document).thenAccept(question -> {
				googleAnswer(question).thenAccept(answer -> {
					postAnswer(answer).thenAccept(status -> {
						if (status == HttpStatus.OK.value()) {
							log.debug("OK");
						} else {
							log.error("Wrong status code: {}", status);
						}
					});
				});
			});
		});
	}

	@Test
	public void thenCompose() throws Exception {
		final CompletableFuture<Document> java = javaQuestions();

		final CompletableFuture<Question> questionFuture =
				java.thenCompose(doc -> findMostInterestingQuestion(doc));

		final CompletableFuture<String> answerFuture =
				questionFuture.thenCompose(question -> googleAnswer(question));

		final CompletableFuture<Integer> httpStatusFuture =
				answerFuture.thenCompose(answer -> postAnswer(answer));

		httpStatusFuture.thenAccept(status -> {
			if (status == HttpStatus.OK.value()) {
				log.debug("OK");
			} else {
				log.error("Wrong status code: {}", status);
			}
		});
	}

	@Test
	public void chained() throws Exception {

		ExecutorService pool =
				Executors.newFixedThreadPool(10);

		CompletableFuture<String> future =
				CompletableFuture.supplyAsync(() ->
						loadWebPage("www.wikipedia.org"),
						pool);

		future.
				thenApply(html -> parse(html)).
				thenCompose(doc -> keywords(doc)).
				thenAccept(System.out::println)
		;

	}

	private String loadWebPage(String s) {
		return null;
	}

	Document parse(String html) {
		return null;
	}

	CompletableFuture<String> keywords(Document doc) {
		//...
		return null;
	}

}
