package com.demo.translator.translatordemo.service;

import com.demo.translator.translatordemo.TranslatorApi;
import com.demo.translator.translatordemo.model.Translation;
import com.demo.translator.translatordemo.repository.TranslatorRepository;
import com.opencsv.CSVWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
@Log4j2
public class TranslatorService implements TranslatorApi {

    @Autowired
    TranslatorRepository repository;

    @Override
    public byte[] translate() {
        byte[] bytes = new byte[]{};
        try {
            List<String[]> translations = repository.populateTranslation();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
            OutputStreamWriter osw = new OutputStreamWriter(bos);
            try (CSVWriter writer = new CSVWriter(osw)) {
                writer.writeAll(translations);
            }
            bytes = bos.toByteArray();
        } catch (Exception e) {
            log.error(e);
        }
        return bytes;
    }

    @Override
    public Page<Translation> getData(Integer pageNo) {
        if (pageNo == null) {
            pageNo = 0;
        }
        int pageSize = 10;
        Pageable pagable = PageRequest.of(pageNo, pageSize);
        return repository.findAll(pagable);
    }

    @Override
    public void saveAll(List<Translation> translations) {
        repository.saveAll(translations);
    }

}
