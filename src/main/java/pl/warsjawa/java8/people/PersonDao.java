package pl.warsjawa.java8.people;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class PersonDao {
	public List<Person> loadPeopleDatabase() throws IOException {
		try (BufferedReader bufferedReader = open("/people.csv")) {
			return Collections.emptyList(); //bufferedReader.lines().
		}
	}

	private BufferedReader open(String fileName) {
		return new BufferedReader(
				new InputStreamReader(
						getClass().getResourceAsStream(fileName),
						StandardCharsets.UTF_8));
	}

}