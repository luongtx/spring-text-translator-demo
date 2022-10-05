package com.demo.translator.translatordemo;

import com.demo.translator.translatordemo.repository.TranslatorRepository;
import com.demo.translator.translatordemo.service.TranslatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TranslatorServiceTest {
    @Autowired
    TranslatorService service;

    @Test
    void testTranslate() {
        service.translate();
    }
}
