package com.demo.translator.translatordemo;

import org.springframework.data.domain.Page;

import com.demo.translator.translatordemo.model.Translation;

public interface TranslatorApi {
	byte[] translate();
	Page<Translation> getData(Integer pageNo);
}
