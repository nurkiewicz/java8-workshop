package com.nurkiewicz.rxjava;

import com.nurkiewicz.rxjava.weather.Weather;
import com.nurkiewicz.rxjava.weather.WeatherStation;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Timestamped;

import java.time.Instant;

@Ignore
public class R47_IndexingTest {

	private static final Logger log = LoggerFactory.getLogger(R47_IndexingTest.class);

	@Test
	public void timestamped() throws Exception {
		final Observable<Timestamped<Weather>> waw = WeatherStation.find("WAW").observations().
				timestamp();
		waw.
				map((Timestamped<Weather> stamped) ->
						Instant.ofEpochMilli(stamped.getTimestampMillis()) + "\t" + stamped.getValue().getTemperature()).
				take(100).
				toBlockingObservable().
				forEach(log::debug);
	}

	@Test
	public void cache() throws Exception {
		final Observable<Weather> cached = WeatherStation.find("WAW").
				observations().
				cache();

		cached.
				timestamp().
				map(t -> Instant.ofEpochMilli(t.getTimestampMillis()) + "\t" + t.getValue().getTemperature()).
				take(5).
				toBlockingObservable().
				forEach(log::debug);

		cached.
				timestamp().
				map(t -> Instant.ofEpochMilli(t.getTimestampMillis()) + "\t" + t.getValue().getTemperature()).
				take(5).
				toBlockingObservable().
				forEach(log::debug);
	}

}
