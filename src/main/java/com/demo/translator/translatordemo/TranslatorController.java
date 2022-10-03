package com.demo.translator.translatordemo;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.translator.translatordemo.model.Translation;


@RestController
public class TranslatorController {

	@Autowired
	TranslatorApi translatorApi;

	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}

	@PostMapping(value="/translate")
	public void convert(HttpServletResponse response) throws IOException {
		String fileExtension = "csv";
        String filename = "translation_en_vi" + fileExtension;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		byte responseData[] = translatorApi.translate();
		if (responseData != null) {
			IOUtils.write(responseData, response.getOutputStream());
        } else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            IOUtils.write("".getBytes(), response.getOutputStream());
        }
	}

	@GetMapping(value="/show-translation")
	public Page<Translation> showTranslation(@RequestParam("page") Integer pageNo) {
		return translatorApi.getData(pageNo);
	}
	
}