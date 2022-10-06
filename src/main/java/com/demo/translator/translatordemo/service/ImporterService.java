package com.demo.translator.translatordemo.service;

import com.demo.translator.translatordemo.ImporterApi;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@Log4j2
@Service
public class ImporterService implements ImporterApi {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob;

    @Override
    public void importCSV(MultipartFile multipartFile) {
        try {
            //Save multipartFile file in a temporary physical folder
            String path = new ClassPathResource("data/").getURL().getPath();
            File fileToImport = new File(path + multipartFile.getOriginalFilename());
            OutputStream outputStream = Files.newOutputStream(fileToImport.toPath());
            IOUtils.copy(multipartFile.getInputStream(), outputStream);
            outputStream.flush();
            outputStream.close();
            //Launch the Batch Job
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", fileToImport.getAbsolutePath())
                    .toJobParameters();
            jobLauncher.run(importUserJob, jobParameters);
        } catch (Exception e) {
            log.error("An error while importing csv", e);
        }
    }
}
