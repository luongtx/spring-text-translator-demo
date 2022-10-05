package com.demo.translator.translatordemo.service;

import com.demo.translator.translatordemo.TranslatorApi;
import com.demo.translator.translatordemo.model.Translation;
import com.demo.translator.translatordemo.repository.TranslatorRepository;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
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
            List<Translation> translations = repository.populateTranslation();
            log.info(translations);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Translation.class);
            String[] columns = new String[]{"id", "text", "translationId", "translateText", "audioUrl"};
            mappingStrategy.setColumnMapping(columns);
            StatefulBeanToCsvBuilder<Translation> builder = new StatefulBeanToCsvBuilder(outputStreamWriter);
            StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build();
            // Write list to StatefulBeanToCsv object
            beanWriter.write(translations);
            bytes = byteArrayOutputStream.toByteArray();
            outputStreamWriter.close();
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
