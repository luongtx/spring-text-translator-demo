package com.demo.translator.translatordemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.translator.translatordemo.TranslatorApi;
import com.demo.translator.translatordemo.model.Translation;
import com.demo.translator.translatordemo.repository.TranslatorRepository;

@Service
public class TranslatorService implements TranslatorApi{

	@Autowired
	TranslatorRepository repository;

	@Override
	public byte[] translate() {
		return null;
	}

	@Override
	public Page<Translation> getData(Integer pageNo) {
		if(pageNo == null) {
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
