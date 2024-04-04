package com.careersforyou.jobservice.web;

import com.careersforyou.jobservice.domain.Job;
import com.careersforyou.jobservice.domain.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping                 // Base handler
    public Iterable<Job> get() {
        return jobService.viewJobList();
    }
    @GetMapping("{jobid}")      // /job/{jobid}
    public Job getByJobid(@PathVariable String jobid) {
        return jobService.viewJobDetails(jobid);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)     // Returns 201 if job is created successfully
    public Job post(@Valid @RequestBody Job job) {return jobService.addJobToDatabase(job);}
    @DeleteMapping("{jobid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns 204 if job is deleted successfully
    public void delete(@PathVariable String jobid) {
        jobService.removeJobFromDatabase(jobid);
    }
    @PutMapping("{jobid}")
    public Job put(@PathVariable String jobid, @Valid @RequestBody Job job) {
        return jobService.editJobDetails(jobid, job);
    }
}