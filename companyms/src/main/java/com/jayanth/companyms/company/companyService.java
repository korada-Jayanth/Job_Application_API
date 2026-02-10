package com.jayanth.companyms.company;



import java.util.List;

public interface companyService {

    List<company> getAllCompaines();
    boolean updateCompany(company company,Long id);
    void createCompany(company company);
    boolean deleteCompany(Long id);
    company getCompanyById(Long id);
}
