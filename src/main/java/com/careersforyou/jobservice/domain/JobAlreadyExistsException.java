package com.careersforyou.jobservice.domain;

public class JobAlreadyExistsException extends RuntimeException {
    public JobAlreadyExistsException(String jobid) {
        super("A job with Jobid " + jobid + " already exists.");
    }
}