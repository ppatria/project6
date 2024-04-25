package com.careersforyou.jobservice.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceTests {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void whenJobToCreateAlreadyExistsThenThrows() {
        var jobidNum = "1234561232";
        var jobToCreate = Job.of(jobidNum, "Title", "Description",
                "Companyname", "Skill1", "Skill2");
        when(jobRepository.existsByJobid(jobidNum)).thenReturn(true);
        assertThatThrownBy(() -> jobService.addJobToDatabase(jobToCreate))
                .isInstanceOf(JobAlreadyExistsException.class)
                .hasMessage("The job with Jobid " + jobidNum + " already exists.");
    }

    @Test
    void whenJobToReadDoesNotExistThenThrows() {
        var jobidNum = "1234561232";
        when(jobRepository.findByJobid(jobidNum)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobService.viewJobDetails(jobidNum))
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("The job with Jobid " + jobidNum + " was not found.");
    }
}

// test for workflow / Github actions