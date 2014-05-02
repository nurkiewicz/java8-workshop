package com.nurkiewicz.java8.stackoverflow;

import com.google.common.base.Throwables;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FallbackStubClient implements StackOverflowClient {

	private static final Logger log = LoggerFactory.getLogger(FallbackStubClient.class);

	private final StackOverflowClient target;

	public FallbackStubClient(StackOverflowClient target) {
		this.target = target;
	}

	@Override
	public String mostRecentQuestionAbout(String tag) {
		try {
			return target.mostRecentQuestionAbout(tag);
		} catch (Exception e) {
			log.warn("Problem retrieving tag {}", tag, e);
			switch(tag) {
				case "java":
					return "How to generate xml report with maven depencency?";
				case "scala":
					return "Update a timestamp SettingKey in an sbt 0.12 task";
				case "groovy":
					return "Reusing Grails variables inside Config.groovy";
				case "clojure":
					return "Merge two comma delimited strings in Clojure";
				default:
					throw e;
			}
		}
	}

	@Override
	public Document mostRecentQuestionsAbout(String tag) {
		try {
			return target.mostRecentQuestionsAbout(tag);
		} catch (Exception e) {
			log.warn("Problem retrieving recent question {}", tag, e);
			return loadStubHtmlFromDisk(tag);
		}
	}

	private Document loadStubHtmlFromDisk(String tag) {
		try {
			final String html = IOUtils.toString(getClass().getResource("/" + tag + "-question.html"));
			return Jsoup.parse(html);
		} catch (IOException e1) {
			throw Throwables.propagate(e1);
		}
	}
}
