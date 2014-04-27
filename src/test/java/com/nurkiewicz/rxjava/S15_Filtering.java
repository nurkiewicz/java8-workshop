package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.stock.StockObservable;
import com.nurkiewicz.rxjava.stock.Transaction;
import com.nurkiewicz.rxjava.util.HeartBeat;
import com.nurkiewicz.rxjava.weather.Weather;
import com.nurkiewicz.rxjava.weather.WeatherStation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class S15_Filtering {

	private static final Logger log = LoggerFactory.getLogger(S15_Filtering.class);

	/**
	 * sample/throttleFirst/throttleLast
	 */
	@Test
	public void sample() throws Exception {
		final Observable<Transaction> observable = StockObservable.observe("IBM");

		observable.
				sample(1, TimeUnit.SECONDS).
				map(Transaction::getPrice).
				take(10).
				toBlockingObservable().
				forEach(p -> log.debug("" + p));
	}

	@Test
	public void distinctUntilChanged() throws Exception {
		WeatherStation.find("WAW").observations().
				map(w -> (int) w.getTemperature()).
				distinctUntilChanged().
				map(Object::toString).
				toBlockingObservable().
				forEach(log::debug);
	}

	@Test
	public void throttleWithTimeout() throws Exception {
		WeatherStation.find("WAW").observations().
				throttleWithTimeout(1, TimeUnit.SECONDS).
				map(Weather::getTemperature).
 				toBlockingObservable().
				forEach(s -> log.debug("Temp: {}", s));
	}

	@Test
	public void timeout() throws Exception {
		final Observable<HeartBeat> observable = HeartBeat.monitorServer("foo");
		observable.
				timeout(1, TimeUnit.SECONDS).
				toBlockingObservable().
				forEach(h -> log.debug("Heart beat: {}", h));
	}

}
