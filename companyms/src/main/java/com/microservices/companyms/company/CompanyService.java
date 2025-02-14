package com.microservices.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    boolean updateCompanyById(Long companyId, Company updatedCompany);

    void createCompany(Company company);

    boolean deleteCompanyById(Long companyId);

    Company getCompanyById(Long companyId);
}
