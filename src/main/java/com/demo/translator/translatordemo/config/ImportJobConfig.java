package com.demo.translator.translatordemo.config;

import com.demo.translator.translatordemo.model.Translation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class ImportJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Translation> reader(@Value("#{jobParameters[filePath]}") String pathToFile) {
        FlatFileItemReader<Translation> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(pathToFile));
        reader.setLineMapper(new DefaultLineMapper<Translation>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "text", "translationId", "translateText", "audioUrl");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Translation>() {{
                setTargetType(Translation.class);
            }});
        }});
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Translation> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Translation> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO " +
                "translation (id, text, translation_id, translate_text, audio_url) " +
                "VALUES (:id, :text, :translationId, :translateText, :audioUrl)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(FlatFileItemReader<Translation> reader, JdbcBatchItemWriter<Translation> writer) {
        return stepBuilderFactory.get("step1")
                .<Translation, Translation>chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
