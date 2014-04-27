package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.stock.StockObservable;
import com.nurkiewicz.rxjava.stock.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Ignore
public class R44_StockExchangeDemoTest {

	private static final Logger log = LoggerFactory.getLogger(R44_StockExchangeDemoTest.class);

	private final Observable<Transaction> twitter = StockObservable.observe("IBM");

	@Test
	public void mapAndFilterButNoSubscriptions() throws Exception {
		final Observable<String> reTweets = twitter.
				map(Transaction::getId).
				filter(text -> text.startsWith("RT")).
				take(1000);
	}

	@Test
	public void nonBlockingSubscription() throws Exception {
		final Observable<String> reTweets = twitter.
				map(Transaction::getId).
				filter(text -> text.startsWith("RT")).
				take(1000);

		reTweets.subscribe(log::debug);
		TimeUnit.MINUTES.sleep(1);
	}

	@Test
	public void blockingSubscription() throws Exception {
		final Observable<String> tweets = twitter.
				map(Transaction::getId).
				filter(text -> text.startsWith("RT")).
				take(100);

		tweets.
				toBlockingObservable().
				forEach(log::debug);
	}

	/**
	 * Also: window()
	 */
	@Test
	public void tweetsPerSecond() throws Exception {
		final Observable<Integer> tweets = twitter.
				buffer(1, TimeUnit.SECONDS).
				map(List::size).
				take(10);

		tweets.
				toBlockingObservable().
				forEach(c -> log.debug("Tweets/s: {}", c));

	}
}