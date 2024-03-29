
package com.careersforyou.jobservice.domain;

import java.util.Optional;

public interface JobRepository {
    Iterable<Job> findAll();
    Optional<Job> findByJobid(String jobid);
    boolean existsByJobid(String jobid);
    Job save(Job job);
    void deleteByJobid(String jobid);
}
