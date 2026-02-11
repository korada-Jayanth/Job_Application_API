package com.jayanth.jobms.job;

import com.jayanth.jobms.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    // GET all jobs
    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    // GET job by id
    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> findById(@PathVariable Long id){
        JobWithCompanyDTO jobWithCompanyDTO = jobService.getJobById(id);
        if(jobWithCompanyDTO != null){
            return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // CREATE job
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    // DELETE job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }

    // UPDATE job
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(
            @PathVariable Long id,
            @RequestBody Job updatedJob
    ){
        boolean updated = jobService.updatedJob(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
