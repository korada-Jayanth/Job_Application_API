package com.jayanth.jobms.job;

import com.jayanth.jobms.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updatedJob(Long id, Job updatedJob);
}
