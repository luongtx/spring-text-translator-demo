package com.demo.translator.translatordemo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.translator.translatordemo.model.Translation;

@Repository
public interface TranslatorRepository extends JpaRepository<Translation, String> {
    Page<Translation> findAll(Pageable pageable);

    @Query(value =
            "SELECT s1.id AS id,\n" +
                    "       s1.text AS text,\n" +
                    "       s2.id AS translation_id,\n" +
                    "       s2.text AS translate_text,\n" +
                    "       CONCAT('https://audio.tatoeba.org/sentences/', s1.lang, '/', s1.id, '.mp3') AS audio_url\n" +
                    "FROM sentences s1\n" +
                    "INNER JOIN links lk ON s1.id=lk.sentence_id\n" +
                    "INNER JOIN sentences s2 ON s2.id=lk.translation_id\n" +
                    "WHERE s1.lang='eng'\n" +
                    "  AND s2.lang='vie';",
            nativeQuery = true)
    List<Translation> populateTranslation();
}
