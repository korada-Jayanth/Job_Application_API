package com.jayanth.jobms.job;

import com.jayanth.jobms.clients.CompanyClient;
import com.jayanth.jobms.clients.ReviewClient;
import com.jayanth.jobms.dto.JobDTO;
import com.jayanth.jobms.external.Review;
import com.jayanth.jobms.external.company;
import com.jayanth.jobms.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;



    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    // GET all jobs
    @Override
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){

        company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        //RestTemplate restTemplate = new RestTemplate();
        /*company company = restTemplate.getForObject(
                "http://COMPANYMS:8082/companies/"+job.getCompanyId(),
                company.class);*/

       /*ResponseEntity<List<Review>> reviewResponse =  restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId=", job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
        });

       List<Review> reviews = reviewResponse.getBody();*/

        JobDTO jobDTO = JobMapper.maptoJobWithCompanyDTO(job,company,reviews);
        //jobDTO.setCompany(company);
    return jobDTO;

    }

    // CREATE job
    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    // GET job by id
    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
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
