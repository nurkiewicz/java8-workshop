package pl.warsjawa.java8;

import org.junit.Test;
import pl.warsjawa.java8.people.Person;
import pl.warsjawa.java8.people.PersonDao;

import java.io.IOException;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.data.Offset.offset;

/**
 * - Various collectors, grouping, average, toList, etc.
 */
public class Lesson05b_CollectorsTest {

	private final PersonDao dao = new PersonDao();

	@Test
	public void calculateAverageHeight() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final Double averageHeight = 0.0; // people.stream().

		assertThat(averageHeight).isEqualTo(174, offset(0.5));
	}

	@Test
	public void partitionByPeopleAboveAndBelow180CmHeight() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final Map<Boolean, List<Person>> peopleByBeight = Collections.emptyMap(); // people.stream().

		final List<Person> tallPeople = peopleByBeight.get(true);
		assertThat(tallPeople).hasSize(33);

		final List<Person> shortPeople = peopleByBeight.get(false);
		assertThat(shortPeople).hasSize(104);
	}

	@Test
	public void groupPeopleByWeight() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final Map<Integer, List<Person>> peopleByWeight = Collections.emptyMap(); // people.stream().

		assertThat(peopleByWeight.get(46)).hasSize(1);
		assertThat(peopleByWeight.get(70)).hasSize(2);
		assertThat(peopleByWeight.get(92)).hasSize(1);
	}

	@Test
	public void weightStatistics() throws IOException {
		final List<Person> people = dao.loadPeopleDatabase();

		final IntSummaryStatistics stats = new IntSummaryStatistics(); // people.stream().

		assertThat(stats.getCount()).isEqualTo(137);
		assertThat(stats.getMin()).isEqualTo(46);
		assertThat(stats.getMax()).isEqualTo(92);
		assertThat(stats.getSum()).isEqualTo(8998);
		assertThat(stats.getAverage()).isEqualTo(65.8, offset(0.5));
	}

}
