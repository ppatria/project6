package com.careersforyou.jobservice.domain;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface JobRepository extends CrudRepository<Job,Long>{
    Optional<Job> findByJobid(String jobid);
    boolean existsByJobid(String jobid);

    @Modifying
    @Transactional
    @Query("delete from Job where jobid = :jobid")
    void deleteByJobid(String jobid);
}
