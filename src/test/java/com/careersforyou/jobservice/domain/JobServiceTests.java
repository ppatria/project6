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
        var jobid = "1234561232";
        var jobToCreate = new Job(jobid, "title", "description",
                "companyname", "skill1" , "skill2");
        when(jobRepository.existsByJobid(jobid)).thenReturn(true);
        assertThatThrownBy(() -> jobService.addJobToDatabase(jobToCreate))
                .isInstanceOf(JobAlreadyExistsException.class)
                .hasMessage("The job with Jobid " + jobid + " already exists.");
    }

    @Test
    void whenJobToReadDoesNotExistThenThrows() {
        var jobid = "1234561232";
        when(jobRepository.findByJobid(jobid)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobService.viewJobDetails(jobid))
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("The job with Jobid " + jobid + " was not found.");
    }
}

// test for workflow / Github actions