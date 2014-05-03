package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.util.HeartBeat;
import com.nurkiewicz.rxjava.weather.Weather;
import com.nurkiewicz.rxjava.weather.WeatherStation;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.TimeInterval;

import java.util.concurrent.TimeUnit;

@Ignore
public class R45_FilteringTest {

	private static final Logger log = LoggerFactory.getLogger(R45_FilteringTest.class);

	/**
	 * sample/throttleFirst/throttleLast
	 */
	@Test
	public void sample() throws Exception {
		final Observable<Weather> observable = WeatherStation.find("WAW").observations();

		observable.
				sample(1, TimeUnit.SECONDS).
				map(Weather::getTemperature).
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

	@Test
	public void timeInterval() throws Exception {
		//given
		final Observable<HeartBeat> observable = HeartBeat.monitorServer("foo")
				.timeout(1, TimeUnit.SECONDS);

		//when
		final Observable<TimeInterval<HeartBeat>> intervals = observable.timeInterval();

		//then
		intervals.toBlockingObservable()
				.forEach((TimeInterval<HeartBeat> i) -> {
					log.debug("Since last {}ms", i.getIntervalInMilliseconds());
				});
	}

}
