package com.demo.translator.translatordemo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.demo.translator.translatordemo.model.Link;
import com.demo.translator.translatordemo.model.Sentence;
import com.demo.translator.translatordemo.model.SentenceAudio;
import com.demo.translator.translatordemo.service.ConverterService;

public class ConverterServiceTest {
    public static String SENTENCES_URI = "data/sentences.csv";
    public static String SENTENCES_AUDIO_URI = "data/sentences_with_audio.csv";
    public static String LINKS_URI = "data/links.csv";

    @Test
    void testConverter_Link() throws IOException {
        InputStream in = getInputStream(LINKS_URI);
        Map<String, Link> map = ConverterService.convertCSV(in, Link.class);
        System.out.println(map);
    }

    @Test
    void testConverter_Sentences() throws IOException {
        InputStream in = getInputStream(SENTENCES_URI);
        Map<String, Sentence> map = ConverterService.convertCSV(in, Sentence.class);
        System.out.println(map);
    }

    @Test
    void testConverter_Sentences_Audio() throws IOException {
        InputStream in = getInputStream(SENTENCES_AUDIO_URI);
        Map<String, SentenceAudio> map = ConverterService.convertCSV(in, SentenceAudio.class);
        System.out.println(map);
    }

    public InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }


    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Connection conn = null;
        Statement stmt = null;

        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
        stmt = conn.createStatement();

        stmt.execute("drop table if exists csvdata");
        stmt.execute("create table csvdata(sentenceId varchar, translationId varchar) as select * from CSVREAD('/home/luongtx/workspace/personal/translator-demo/src/main/resources/data/links.csv')");
        ResultSet rs = stmt.executeQuery("select * from csvdata");

        while (rs.next()) {
            System.out.println(rs.getString(0)+ " "+rs.getString(1));
        }
        stmt.close();
        long elapse = System.currentTimeMillis() - startTime;
        System.out.println("Elapse time: "+elapse);
    }

}
