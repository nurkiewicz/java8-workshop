package com.nurkiewicz.rxjava.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HeartBeat {

	private static final Logger log = LoggerFactory.getLogger(HeartBeat.class);

	private static final AtomicInteger COUNTER = new AtomicInteger();

	private final int id = COUNTER.incrementAndGet();

	public static Observable<HeartBeat> monitorServer(String name) {
		return Observable.create((Observable.OnSubscribe<HeartBeat>)subscriber -> {
			Thread t = new Thread("HeartBeat") {
				@Override
				public void run() {
					for (int i = 1; i <= 20; ++i) {
						try {
							TimeUnit.MILLISECONDS.sleep(i * 100);
							subscriber.onNext(new HeartBeat());
						} catch (InterruptedException e) {
							log.warn("Interrupted");
						}
					}
				}
			};
			t.setDaemon(true);
			t.start();
			subscriber.add(Subscriptions.create(t::interrupt));
		});
	}

	@Override
	public String toString() {
		return "HeartBeat{id=" + id + '}';
	}
}
