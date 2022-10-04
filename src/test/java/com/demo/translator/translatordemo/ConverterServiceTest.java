package com.demo.translator.translatordemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
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
    @Disabled
    void testConverter_Link() throws IOException {
        InputStream in = getInputStream(LINKS_URI);
        Map<String, Link> map = ConverterService.convertCSV(in, Link.class);
        System.out.println(map);
    }

    @Test
    @Disabled
    void testConverter_Sentences() throws IOException {
        InputStream in = getInputStream(SENTENCES_URI);
        Map<String, Sentence> map = ConverterService.convertCSV(in, Sentence.class);
        System.out.println(map);
    }

    @Test
    @Disabled
    void testConverter_Sentences_Audio() throws IOException {
        InputStream in = getInputStream(SENTENCES_AUDIO_URI);
        Map<String, SentenceAudio> map = ConverterService.convertCSV(in, SentenceAudio.class);
        System.out.println(map);
    }

    public InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }


    public static void main(String[] args) throws Exception {
        int split;
        int s = 20;
        File folder = new File("/home/luongtx/workspace/personal/spring-text-translator-demo/src/main/resources/data/");                                //*** Location of your file
        int filecount = 0;
        for (File fo : folder.listFiles()) {
            if (fo.isFile()) {
                filecount++;
            }
        }
        System.out.println("Total source file count is :-----------------------    " + filecount + "\n");  //*** Total numbr of orginal file in mentioned folder
        String path = folder.getAbsolutePath();
        // System.out.println("location=-----------"+path);
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("File name Is :--------------------------   " + listOfFile.getName());  //*** File name
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "/" + listOfFile.getName()));   // Read a souce file
                String input;
                int count = 0;
                while ((input = bufferedReader.readLine()) != null) {
                    count++;
                }
                System.out.println("File total rows count is :--------------   " + count);   //*** Number of row count in the file
                split = count / s;

                int n = split, z = 0;
                if (n != z) {
                    System.out.println("Each splitted file line count is :------   " + split + "\n"); //*** After splitted  file have the rows count
                    FileInputStream fstream = new FileInputStream(path + "/" + listOfFile.getName());
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    for (int j = 1; j <= s; j++) {
                        File dir = new File(path + "/" + "CSV_DATA_" + j);
                        dir.mkdir();
                        File filefolder = new File(path + "/" + "CSV_DATA_" + j);
                        String folderpath = filefolder.getAbsolutePath();
                        //********Destination File Location******
                        FileWriter fstream1 = new FileWriter(folderpath + "/" + listOfFile.getName());   //*** Splitted files  and file format(.txt/csv.....)
                        BufferedWriter out = new BufferedWriter(fstream1);
                        for (int i = 1; i <= n; i++) {
                            strLine = br.readLine();
                            if (strLine != null) {
                                out.write(strLine);
                                if (i != n) {
                                    out.newLine();
                                }
                            }
                        }
                        out.close();
                    }
                    in.close();
                } else {// Below N number of row in this file
                    System.out.println("\n******************************* Mentioned this file have below - " + s + " rows   ******************************   " + listOfFile.getName() + " \n");
                }
            }
        }
        System.out.println("\n Splitted_CSV_files stored location is :     " + path);
    }

}
