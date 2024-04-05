package com.careersforyou.jobservice.domain;

public class JobAlreadyExistsException extends RuntimeException {
    public JobAlreadyExistsException(String jobid) {
        super("The job with Jobid " + jobid + " already exists.");
    }
}