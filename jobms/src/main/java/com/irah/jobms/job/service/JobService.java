package com.irah.jobms.job.service;

import com.irah.jobms.job.model.JobDto;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<JobDto> findAllJobs();
    JobDto createJob(JobDto jobDto);



    Optional<JobDto> findJobById(Long jobId);

    Optional<JobDto> updateJob(Long jobId, JobDto jobDto);


    boolean deleteJobById(Long jobId);
}
