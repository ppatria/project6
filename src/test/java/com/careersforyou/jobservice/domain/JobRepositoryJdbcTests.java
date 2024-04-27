/* package com.careersforyou.jobservice.domain;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.careersforyou.jobservice.config.DataConfig;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class JobRepositoryJdbcTests {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findAllJobs() {
        var job1 = Job.of("1234561235", "Title", "Author", "Companyname", "Skill1", "Skill2");
        var job2 = Job.of("1234561236", "Another Title", "Author", "Companyname", "Skill1", "Skill2");
        jdbcAggregateTemplate.insert(job1);
        jdbcAggregateTemplate.insert(job2);

        Iterable<Job> actualJobs = jobRepository.findAll();

        assertThat(StreamSupport.stream(actualJobs.spliterator(), true)
                .filter(job -> job.jobid().equals(job1.jobid()) || job.jobid().equals(job2.jobid()))
                .collect(Collectors.toList())).hasSize(2);
    }

    @Test
    void findJobByJobidWhenExisting() {
        var jobidNum = "1234561237";
        var job = Job.of(jobidNum, "Title", "Author", "Companyname", "Skill1", "Skill2");
        jdbcAggregateTemplate.insert(job);

        Optional<Job> actualJob = jobRepository.findByJobid(jobidNum);

        assertThat(actualJob).isPresent();
        assertThat(actualJob.get().jobid()).isEqualTo(job.jobid());
    }

    @Test
    void findJobByJobidWhenNotExisting() {
        Optional<Job> actualJob = jobRepository.findByJobid("1234561238");
        assertThat(actualJob).isEmpty();
    }

    @Test
    void existsByJobidWhenExisting() {
        var jobidNum = "1234561239";
        var jobToCreate = Job.of(jobidNum, "Title", "Author", "Companyname", "Skill1", "Skill2");
        jdbcAggregateTemplate.insert(jobToCreate);

        boolean existing = jobRepository.existsByJobid(jobidNum);

        assertThat(existing).isTrue();
    }

    @Test
    void existsByJobidWhenNotExisting() {
        boolean existing = jobRepository.existsByJobid("1234561240");
        assertThat(existing).isFalse();
    }

    @Test
    void deleteByJobid() {
        var jobidNum = "1234561241";
        var jobToCreate = Job.of(jobidNum, "Title", "Author", "Companyname", "Skill1", "Skill2");
        var persistedJob = jdbcAggregateTemplate.insert(jobToCreate);

        jobRepository.deleteByJobid(jobidNum);

        assertThat(jdbcAggregateTemplate.findById(persistedJob.id(), Job.class)).isNull();
    }

}
*/