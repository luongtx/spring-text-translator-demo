package com.demo.translator.translatordemo;

import com.demo.translator.translatordemo.repository.TranslatorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TranslatorRepositoryTest {

    @Autowired
    TranslatorRepository repository;

    @Test
    void testTranslator() {
        List<String[]> translations = repository.populateTranslation();
        translations.forEach(
                (row) -> {
                    for (String data : row) {
                        System.out.print(data + " ");
                    }
                    System.out.println();
                }
        );
    }
}
