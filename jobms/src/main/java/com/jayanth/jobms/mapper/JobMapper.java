package com.jayanth.jobms.mapper;

import com.jayanth.jobms.dto.JobWithCompanyDTO;
import com.jayanth.jobms.job.Job;
import com.jayanth.jobms.external.company;
public class JobMapper {
    public static JobWithCompanyDTO maptoJobWithCompanyDTO(
            Job job,
            company company) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
