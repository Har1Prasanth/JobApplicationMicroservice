package com.irah.jobms.job.controller;

import com.irah.jobms.job.model.JobDto;
import com.irah.jobms.job.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {

    private JobService jobService;



    //Get All jobs
    @GetMapping
    public ResponseEntity<List<JobDto>> findAllJobs() {

        return ResponseEntity.ok(jobService.findAllJobs());
    }

    //Create a job
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobDto jobDto) {
        jobService.createJob(jobDto);
        return ResponseEntity.ok("Job Added Successfully");
    }

    //Find By ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<JobDto>> findJobById(@PathVariable("id") Long jobId){
        Optional<JobDto> jobDto=jobService.findJobById(jobId);

            return ResponseEntity.ok(jobDto);

      //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //Update Job
    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable("id") Long jobId, @RequestBody JobDto jobDto){
        Optional<JobDto> job1=jobService.updateJob(jobId,jobDto);
        if(job1.isPresent())
            return ResponseEntity.ok(job1.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Delete Job by Id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable("id") Long jobId){

        boolean flag = jobService.deleteJobById(jobId);
        if(flag){
            return ResponseEntity.ok("Job with ID "+jobId+ " Deleted Successfully");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
