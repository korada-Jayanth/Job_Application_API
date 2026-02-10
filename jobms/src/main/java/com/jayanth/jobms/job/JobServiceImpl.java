package com.jayanth.jobms.job;

import com.jayanth.jobms.dto.JobWithCompanyDTO;
import com.jayanth.jobms.external.company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // GET all jobs
    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);

        RestTemplate restTemplate = new RestTemplate();
        company company = restTemplate.getForObject(
                "http://localhost:8082/companies/"+job.getCompanyId(),
                company.class);
        jobWithCompanyDTO.setCompany(company);
    return jobWithCompanyDTO;

    }

    // CREATE job
    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    // GET job by id
    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // DELETE job
    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // UPDATE job
    @Override
    public boolean updatedJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
