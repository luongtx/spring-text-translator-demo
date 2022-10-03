package com.demo.translator.translatordemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslatorApi {
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}
}