package com.example.send_book_email_spring_batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;

public class SendBookLoanNoiticationJobConfig {

    @Bean
    public Job sendBookLoanNoiticationJob(Step sendEmailUserStep, JobRepository jobRepository) {
        return new JobBuilder("sendBookLoanNoiticationJob", jobRepository)
                .start(sendEmailUserStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
