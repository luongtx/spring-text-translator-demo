package com.demo.translator.translatordemo;

import org.springframework.web.multipart.MultipartFile;

public interface ImporterApi {
    void importCSV(MultipartFile multipartFile);
}
