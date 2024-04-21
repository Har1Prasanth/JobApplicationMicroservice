package com.irah.companyms.company.service;

import com.irah.companyms.company.model.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyDto> getAllCompanies();

    Optional<CompanyDto> updateCompany(Long companyId, CompanyDto companyDto);
    CompanyDto addCompany(CompanyDto companyDto);

    boolean deleteCompanyById(Long companyId);

    Optional<CompanyDto> findCompanyById(Long companyId);
}
