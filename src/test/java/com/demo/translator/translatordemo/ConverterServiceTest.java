package com.demo.translator.translatordemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.demo.translator.translatordemo.model.Link;
import com.demo.translator.translatordemo.model.Sentence;
import com.demo.translator.translatordemo.model.SentenceAudio;
import com.demo.translator.translatordemo.service.ConverterService;

public class ConverterServiceTest {
	public static String SENTENCES_URL = "https://drive.google.com/uc?export=download&id=1kYgGZqRs15Sr44k5S3M74hTwil-oWEmY";
	public static String SENTENCES_AUDIO_URL = "https://drive.google.com/uc?export=download&id=1j_9tp80RRe4oEhvQkIJhe-k9Y3md8ghM";
	public static String LINKS_URL = "https://drive.google.com/uc?export=download&id=1Uq9m48o8GApjEq1NWE2iUqWUrCpG_K6b";

	@Test
	void testConverter_Link() throws IOException {
		InputStream in = getInputStream(LINKS_URL);
		Map<String, Link> map = ConverterService.convertCSV(in, Link.class);
		System.out.println(map);
	}

	@Test
	void testConverter_Sentences() throws IOException {
		InputStream in = getInputStream(SENTENCES_URL);
		Map<String, Sentence> map = ConverterService.convertCSV(in, Sentence.class);
		System.out.println(map);
	}

	@Test
	void testConverter_Sentences_Audio() throws IOException {
		InputStream in = getInputStream(SENTENCES_AUDIO_URL);
		Map<String, SentenceAudio> map = ConverterService.convertCSV(in, SentenceAudio.class);
		System.out.println(map);
	}

	public static InputStream getInputStream(String url) throws IOException {
		return new BufferedInputStream(new URL(LINKS_URL).openStream());
	}

}
