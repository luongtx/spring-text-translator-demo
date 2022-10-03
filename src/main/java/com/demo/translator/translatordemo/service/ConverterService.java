package com.demo.translator.translatordemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ConverterService {

	public static <T> Map<String, T> convertCSV(InputStream is, Class<T> clazz) {
		Map<String, T> res = new HashMap<>();
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.newFormat('\t'));) {

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			List<Field> fields = getAllFields(clazz);
			for (CSVRecord csvRecord : csvRecords) {
				T t = clazz.newInstance(); 
				int index = 0;
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(t, csvRecord.get(index));
					index++;
				}
				res.put(csvRecord.get(0), t);
			}
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }
	
}
