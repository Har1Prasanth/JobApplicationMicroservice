package com.irah.jobms.job.service;

import com.irah.jobms.job.entity.Job;
import com.irah.jobms.job.external.Company;
import com.irah.jobms.job.external.Review;
import com.irah.jobms.job.mapper.JobMapper;
import com.irah.jobms.job.model.JobDto;
import com.irah.jobms.job.repository.JobRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.irah.jobms.job.mapper.JobMapper.mapToJobDto;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepo jobRepository;
    private RestTemplate restTemplate;


    @Override
    public List<JobDto> findAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> convertToJobWithCompanyDto(job))
                .collect(Collectors.toList());
    }


    @Override
    public JobDto createJob(JobDto jobDto) {
        Job job = jobRepository.save(JobMapper.mapToJob(jobDto));
        return mapToJobDto(job);
    }

    @Override
    public Optional<JobDto> findJobById(Long jobId) {
        Optional<Job> job = jobRepository.findById(jobId);

        if (job.isPresent()){
            return Optional.ofNullable(convertToJobWithCompanyDto(job.get()));
         }
        return Optional.empty();
    }

    @Override
    public Optional<JobDto> updateJob(Long jobId, JobDto jobDto) {
        Optional<Job> job1 = jobRepository.findById(jobId);
        if (job1.isPresent()) {
            Job existingJob = job1.get();
            existingJob.setDescription(jobDto.getDescription());
            existingJob.setTitle(jobDto.getTitle());
            existingJob.setMinSalary(jobDto.getMinSalary());
            existingJob.setMaxSalary(jobDto.getMaxSalary());
            existingJob.setLocation(jobDto.getLocation());
            jobRepository.save(existingJob);
            return Optional.of(mapToJobDto(existingJob));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteJobById(Long jobId) {
        Optional<Job> job = jobRepository.findById(jobId);

        if (job.isPresent()) {
            jobRepository.deleteById(jobId);
            return true;
        }
        return false;
    }

    public JobDto convertToJobWithCompanyDto(Job job) {

        //RestTemplate with Eureaka Services
        JobDto jobDto = new JobDto();
        jobDto = mapToJobDto(job);
        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
                Company.class);


        ResponseEntity<List<Review>> reviewResponse= restTemplate.exchange("http://REVIEW-SERVICE:8082/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews= reviewResponse.getBody();
        jobDto.setCompany(company);
        jobDto.setReviews(reviews);
        return jobDto;
    }


}
