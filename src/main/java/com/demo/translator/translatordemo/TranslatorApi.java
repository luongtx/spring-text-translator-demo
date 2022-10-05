package com.demo.translator.translatordemo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;

import com.demo.translator.translatordemo.model.Translation;

public interface TranslatorApi {
	byte[] translate();
	Page<Translation> getData(Integer pageNo);
	void saveAll(List<Translation> translations);
	List<Translation> getAllTranslation();
	CompletableFuture<Boolean> importToDb(List<Translation> translations);
}
