package com.microservices.companyms.company.impl;

import com.microservices.companyms.company.Company;
import com.microservices.companyms.company.CompanyRepository;
import com.microservices.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long companyId, Company updatedCompany) {
        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            Company comp = company.get();
            comp.setDescription(updatedCompany.getDescription());
            comp.setName(updatedCompany.getName());
            companyRepository.save(comp);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        company.setId(null);
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long companyId) {
        if(companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

}
