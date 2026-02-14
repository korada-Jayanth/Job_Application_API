package com.jayanth.jobms.clients;


import com.jayanth.jobms.external.company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient {

      @GetMapping("/companies/{id}")  // ✅ CORRECT
      company getCompany(@PathVariable Long id);
}
