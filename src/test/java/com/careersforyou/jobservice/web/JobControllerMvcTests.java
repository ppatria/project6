package com.careersforyou.jobservice.web;

import com.careersforyou.jobservice.domain.JobNotFoundException;
import com.careersforyou.jobservice.domain.JobService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
public class JobControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    void whenGetJobNotExistingThenShouldReturn404() throws Exception {
        String jobid = "73737313940";
        given(jobService.viewJobDetails(jobid)).willThrow(JobNotFoundException.class);
        mockMvc
                .perform(get("/jobs/" + jobid))
                .andExpect(status().isNotFound());
    }
}

