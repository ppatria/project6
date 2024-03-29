package com.careersforyou.jobservice.domain;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String jobid) {
        super("The job with Jobid " + jobid + " was not found.");
    }
}