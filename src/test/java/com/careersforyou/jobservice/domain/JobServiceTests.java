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
//jhgfkfjghkjhf
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void whenBookToCreateAlreadyExistsThenThrows() {
        var jobid = "1234561232";
        var jobToCreate = new Job("1234567890", "title", "description",
                "companyname", "skill1" , "skill2");
        when(jobRepository.existsByJobid(jobid)).thenReturn(true);
        assertThatThrownBy(() -> jobService.addJobToDatabase(jobToCreate))
                .isInstanceOf(JobAlreadyExistsException.class)
                .hasMessage("A book with ISBN " + jobid + " already exists.");
    }

    @Test
    void whenBookToReadDoesNotExistThenThrows() {
        var jobid = "1234561232";
        when(jobRepository.findByJobid(jobid)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobService.viewJobDetails(jobid))
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("The book with ISBN " + jobid + " was not found.");
    }
}