package com.microservices.jobms.job;

import com.microservices.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
//    List<Job> findAll();
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
