package com.nurkiewicz.rxjava;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

@Ignore
public class R42_CreatingTest {

	private static final Logger log = LoggerFactory.getLogger(R42_CreatingTest.class);

	/**
	 * Observable.from(Iterable)
	 * - subscribe and see execution Thread in logger
	 */
	@Test
	public void fromCollection() {
		final Observable<Integer> obs = Observable.from(1, 2, 3, 4, 5);
		obs.
				map(i -> i * 10).       //10, 20, 30... 50
				filter(i -> i > 20).    //30...50
				flatMap(i -> Observable.from(i, -i)).  //30, -30...
				map(Object::toString).
				subscribe(log::debug);
	}

	@Test
	public void range() throws InterruptedException {
		Observable.range(1, 100).
				map(i -> i * 10).
				filter(i -> i > 20).
				flatMap(i -> Observable.from(i, -i)).
				map(Object::toString).
				take(10).
				subscribe(log::debug);
	}

	@Test
	public void interval() throws Exception {
		Observable.interval(1, TimeUnit.SECONDS).
				map(i -> i * 10).
				flatMap(i -> Observable.from(i, -i)).
				map(Object::toString).
				subscribe(log::debug);

		TimeUnit.MINUTES.sleep(1);
	}

	@Test
	public void fromFunction() throws Exception {
		final Observable<Integer> obs = Observable.create((Subscriber<? super Integer> subscriber) -> {
			log.debug("Someone subscribed");
			subscriber.onNext(1);
			subscriber.onNext(2);
			subscriber.onNext(3);
			subscriber.onCompleted();
		});

		obs.subscribe(i -> log.debug("" + i));
		obs.subscribe(i -> log.debug("" + i));
	}

}
