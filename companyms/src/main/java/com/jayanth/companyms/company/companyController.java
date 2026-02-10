package com.jayanth.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class companyController {

    public companyService companyService;

    public companyController(companyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<company>>getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompaines(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody company company){
        companyService.updateCompany(company,id);
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody company company){
        companyService.createCompany(company);

        return new ResponseEntity<>("Company added Successfully.",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompany(id);
        if(isDeleted){
            return new ResponseEntity<>("Company deleted Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Company not found",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<company> getCompanyById(@PathVariable Long id){
        company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
