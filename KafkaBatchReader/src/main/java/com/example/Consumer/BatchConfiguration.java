package com.example.Consumer;

import com.example.producer.SalseInfoDto;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;
    private final ConsumerFactory<String, SalseInfoDto> consumerFactory;
    private final KafkaItemReader<String, SalseInfoDto> reader;

    //job
    @Bean
    public Job job() throws Exception {
        return new JobBuilder("job", jobRepository())
                .incrementer(new RunIdIncrementer())
                .start(consumerStep())
                .build();
    }

    //step
    public Step consumerStep() throws Exception {
        return new StepBuilder("consumerStep", jobRepository())
                .<SalseInfoDto, SalseInfoEntity>chunk(10, transactionManager())
                .reader(reader)
                .writer(writer())
                .build();
    }

    @Bean
    public KafkaItemReader<String, SalseInfoDto> reader() {
        return new KafkaItemReaderBuilder<String, SalseInfoDto>()
                .name("reader")
                .topic("salseInfo")
                .consumerProperties((Properties) consumerFactory)
                .build();
    }

    @Bean
    public JpaItemWriter<SalseInfoEntity> writer(){
        return new JpaItemWriterBuilder<SalseInfoEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory);
    }


    //job-repo
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType("MYSQL");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager());
        jobRepositoryFactoryBean.afterPropertiesSet();
        return jobRepositoryFactoryBean.getObject();
    }
}
