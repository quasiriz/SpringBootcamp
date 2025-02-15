package com.microservices.jobms.job.impl;


import com.microservices.jobms.job.Job;
import com.microservices.jobms.job.JobRepository;
import com.microservices.jobms.job.JobService;
import com.microservices.jobms.job.dto.JobDTO;
import com.microservices.jobms.job.external.Company;
import com.microservices.jobms.job.external.Review;
import com.microservices.jobms.job.mapper.JobMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
    }

//    Non DTO version
//    @Override
//    public List<Job> findAll() {
//        RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
//        System.out.println("COMPANY"+company.getName()+company.getDescription());
//        return jobRepository.findAll();
//    }

    private JobDTO convertToDTO(Job job) {

//            RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject(
                "http://COMPANYMS/companies/" + job.getCompanyId(), Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = reviewResponse.getBody();
        return  JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
    }


    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        job.setId(null);
        jobRepository.save(job);
    }

//    @Override
//    public Job getJobById(Long id) {
//        return jobRepository.findById(id).orElse(null);
//    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

}
