package com.demo.translator.translatordemo;

import com.demo.translator.translatordemo.model.Translation;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class TranslatorController {

    @Autowired
    TranslatorApi translatorApi;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping(value = "/translate")
    @ApiOperation(value = "return the csv file from aggregating csv documents")
    public void getTranslatedCSV(HttpServletResponse response) throws IOException {
        String fileExtension = "csv";
        String filename = String.format("translation_en_vi.%s", fileExtension);
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        byte[] responseData = translatorApi.translate();
        if (responseData != null) {
            IOUtils.write(responseData, response.getOutputStream());
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            IOUtils.write("".getBytes(), response.getOutputStream());
        }
    }

    @GetMapping(value = "/show-translation")
    @ApiOperation(value = "show translation with paged format")
    public Page<Translation> showTranslation(@RequestParam("page") Integer pageNo) {
        return translatorApi.getData(pageNo);
    }
}