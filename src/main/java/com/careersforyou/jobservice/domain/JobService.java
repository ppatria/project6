
package com.careersforyou.jobservice.domain;

import org.springframework.stereotype.Service;
@Service
public class JobService {
    private final JobRepository jobRepository;		// Sets up repository
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public Iterable<Job> viewJobList() {			// Use case viewJobList
        return jobRepository.findAll();
    }
    public Job viewJobDetails(String jobid) {		// Use case viewJobDetails
        return jobRepository.findByJobid(jobid)
                .orElseThrow(() -> new JobNotFoundException(jobid));
    }
    public Job addJobToDatabase(Job job) {		// Use case addJobToCatalog
        if (jobRepository.existsByJobid(job.jobid())) {
            throw new JobAlreadyExistsException(job.jobid());
        }
        return jobRepository.save(job);
    }
    public void removeJobFromDatabase(String jobid) {	// Use case removeJobFromDatabase
        jobRepository.deleteByJobid(jobid);
    }
    public Job editJobDetails(String jobid, Job job) {	// Use case editJobDetails
        return jobRepository.findByJobid(jobid)
                .map(existingJob -> {
                    var jobToUpdate = new Job(
                            existingJob.jobid(),
                            job.title(),
                            job.description(),
                            job.companyname(),
                            job.skill1(),
                            job.skill2());
                    return jobRepository.save(jobToUpdate);
                })
                .orElseGet(() -> addJobToDatabase(job));
    }
}
