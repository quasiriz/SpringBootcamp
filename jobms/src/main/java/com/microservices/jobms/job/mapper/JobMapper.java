package com.microservices.jobms.job.mapper;

import com.microservices.jobms.job.Job;
import com.microservices.jobms.job.dto.JobDTO;
import com.microservices.jobms.job.external.Company;
import com.microservices.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompany(company);
        jobDTO.setDescription(job.getDescription());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
