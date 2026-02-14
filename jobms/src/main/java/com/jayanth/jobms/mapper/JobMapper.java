package com.jayanth.jobms.mapper;

import com.jayanth.jobms.dto.JobDTO;
import com.jayanth.jobms.external.Review;
import com.jayanth.jobms.job.Job;
import com.jayanth.jobms.external.company;

import java.util.List;

public class JobMapper {
    public static JobDTO maptoJobWithCompanyDTO(
            Job job,
            company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
