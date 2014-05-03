package com.nurkiewicz.rxjava;

import com.google.common.collect.Lists;
import com.nurkiewicz.rxjava.weather.Weather;
import com.nurkiewicz.rxjava.weather.WeatherStation;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
public class R46_ComposingTest {

	private static final Logger log = LoggerFactory.getLogger(R46_ComposingTest.class);

	private final Observable<Weather> warsaw = WeatherStation.find("WAW").observations();
	private final Observable<Weather> krakow = WeatherStation.find("KRA").observations();

	@Test
	public void merge() throws Exception {
		Observable.merge(warsaw, krakow).
				map(w -> w.getStationId() + ":\t" + w.getTemperature()).
				take(100).
				toBlockingObservable().
				forEach(log::debug);
	}

	@Test
	public void zip() throws Exception {
		final Observable<Float> averageTemp = Observable.zip(
				warsaw.map(Weather::getTemperature),
				krakow.map(Weather::getTemperature),
				(w, k) -> (w + k) / 2
		);
	}

	@Test
	public void combineLatest() throws Exception {
		final Observable<Float> averageTemp = Observable.combineLatest(
				warsaw.map(Weather::getTemperature),
				krakow.map(Weather::getTemperature),
				(w, k) -> (w + k) / 2
		);
	}

	@Test
	public void shouldConcatTwoStreams() throws Exception {
		//given
		final Observable<String> first = Observable.from("A", "B", "C");
		Observable<String> second = Observable.range(0, 3).map(Object::toString);

		//when
		final Observable<String> joined = Observable.concat(first, second);

		//then
		List<String> joinedList = Lists.newArrayList(
				joined.toBlockingObservable().toIterable());
		assertThat(joinedList).containsExactly("A", "B", "C", "0", "1", "2");
	}

}
