package com.irah.jobms.job.mapper;

import com.irah.jobms.job.clients.CompanyClient;
import com.irah.jobms.job.entity.Job;
import com.irah.jobms.job.external.Company;
import com.irah.jobms.job.external.Review;
import com.irah.jobms.job.model.JobDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class JobMapper {

    private static CompanyClient companyClient;

    public static JobDto mapToJobDto(Job job) {
        JobDto jobDto = new JobDto();
        BeanUtils.copyProperties(job, jobDto);
        return jobDto;
    }

    public static Job mapToJob(JobDto jobDto) {
        Job job = new Job();
        BeanUtils.copyProperties(jobDto, job);
        return job;
    }


}
