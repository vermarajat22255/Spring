package com.notification.batch;

import com.notification.Entity.NotificationEntity;
import com.notification.repository.NotificationRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableBatchProcessing
@ComponentScan
@EnableAutoConfiguration
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    NotificationRepository notificationRepository;

    @Bean
    public RepositoryItemReader<NotificationEntity> repositoryItemReader() {
        Map<String, Sort.Direction> map = new HashMap<>();
        map.put("notificationId", Sort.Direction.DESC);
        RepositoryItemReader<NotificationEntity> repositoryItemReader = new RepositoryItemReader<>();
        repositoryItemReader.setRepository(notificationRepository);
        repositoryItemReader.setPageSize(5);
        repositoryItemReader.setMethodName("findAll");
        repositoryItemReader.setSort(map);
        return repositoryItemReader;
    }
    @Bean
    public ItemProcessor<NotificationEntity, NotificationEntity> getItemProcessor(){
        return new NotificationProcessor();
    }
    @Bean
    public RepositoryItemWriter<NotificationEntity> repositoryItemWriter() {
        RepositoryItemWriter<NotificationEntity> peopleRepositoryItemWriter = new RepositoryItemWriter<>();
        peopleRepositoryItemWriter.setRepository(notificationRepository);
        peopleRepositoryItemWriter.setMethodName("save");
        return peopleRepositoryItemWriter;
    }

    @Bean
    public Job getJob(JobBuilderFactory jobBuilderFactory, Step step1) {
        return jobBuilderFactory
                .get("import")
                .incrementer(new RunIdIncrementer()) // because a spring config bug, this incrementer is not really useful
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, RepositoryItemReader<NotificationEntity> reader,
                      RepositoryItemWriter<NotificationEntity> writer, ItemProcessor<NotificationEntity, NotificationEntity> processor) {
        return stepBuilderFactory.get("step1")
                .<NotificationEntity, NotificationEntity>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}