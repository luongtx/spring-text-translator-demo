package com.demo.translator.translatordemo.service;

import com.demo.translator.translatordemo.ImporterApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Log4j2
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
