package com.careersforyou.jobservice.demo;

import java.util.List;

import com.careersforyou.jobservice.domain.Job;
import com.careersforyou.jobservice.domain.JobRepository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class JobDataLoader {

    private final JobRepository jobRepository;

    public JobDataLoader(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadJobTestData() {
        jobRepository.deleteAll();
        var job1 = Job.of("1234567891", "Teacher", "Teach algebra", "ACC", "math", "computing");
        var job2 = Job.of("1234567892", "Pilot", "Fly planes", "Boeing", "navigate", "dont fall");
        jobRepository.save(job1);
        jobRepository.save(job2);
    }

}