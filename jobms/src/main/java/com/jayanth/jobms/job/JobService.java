package com.jayanth.jobms.job;

import com.jayanth.jobms.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updatedJob(Long id, Job updatedJob);
}
