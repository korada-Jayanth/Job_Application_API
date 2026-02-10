package com.jayanth.jobms.dto;


import com.jayanth.jobms.job.Job;
import com.jayanth.jobms.external.company;
public class JobWithCompanyDTO {
    private Job job;
    private company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public company getCompany() {
        return company;
    }

    public void setCompany(company company) {
        this.company = company;
    }
}
