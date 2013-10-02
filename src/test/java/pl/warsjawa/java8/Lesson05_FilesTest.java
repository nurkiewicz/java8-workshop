package pl.warsjawa.java8;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * - More complex operations on Stream, (what is Stream)?
 * - Comparator
 */
public class Lesson05_FilesTest {

	@Test
	public void hello() throws Exception {
		try (BufferedReader bufferedReader = open("/people.csv")) {
			//...
		}
	}

	private BufferedReader open(String fileName) {
		return new BufferedReader(
				new InputStreamReader(
						getClass().getResourceAsStream(fileName),
						StandardCharsets.UTF_8));
	}


}
