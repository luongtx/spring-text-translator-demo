package com.demo.translator.translatordemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.translator.translatordemo.model.Translation;

@Repository
public interface TranslatorRepository extends JpaRepository<Translation, String> {
	Page<Translation> findAll(Pageable pageable);
}
