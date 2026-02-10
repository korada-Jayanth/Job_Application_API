package com.jayanth.companyms.company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class companyServiceImpl implements companyService{
    private companyRepository companyRepository;

    public companyServiceImpl(companyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<company> getAllCompaines(){
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(company company,Long id) {
        Optional<company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            company companytoupdate = companyOptional.get();
            companytoupdate.setDescription(company.getDescription());
            companytoupdate.setName(company.getName());
            companyRepository.save(companytoupdate);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void createCompany(company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public company getCompanyById(Long id) {
         return companyRepository.findById(id).orElse(null);
    }
}
