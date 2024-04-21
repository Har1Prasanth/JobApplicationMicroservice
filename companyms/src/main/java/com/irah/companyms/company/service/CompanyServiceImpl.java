package com.irah.companyms.company.service;

import com.irah.companyms.company.entity.Company;
import com.irah.companyms.company.mapper.CompanyMapper;
import com.irah.companyms.company.model.CompanyDto;
import com.irah.companyms.company.repository.CompanyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepo companyRepo;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies= companyRepo.findAll();

        return companies
                .stream()
                .map(company -> CompanyMapper.mapToCompanyDto(company))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanyDto> updateCompany(Long companyId, CompanyDto companyDto) {

        Optional<Company> company = companyRepo.findById(companyId);
        if (company.isPresent()) {
            Company existingCompany = company.get();
            existingCompany.setDescription(companyDto.getDescription());
            existingCompany.setName(companyDto.getName());
         //   existingCompany.setJobs(companyDto.getJobs());
            companyRepo.save(existingCompany);
            return Optional.of(CompanyMapper.mapToCompanyDto(existingCompany));
        }
        return Optional.empty();
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        Company company=companyRepo.save(CompanyMapper.mapToCompany(companyDto));
        return CompanyMapper.mapToCompanyDto(company);
    }

    @Override
    public boolean deleteCompanyById(Long companyId) {
        if(companyRepo.existsById(companyId)){
            companyRepo.deleteById(companyId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CompanyDto> findCompanyById(Long companyId) {
        Optional<Company> company = companyRepo.findById(companyId);

        if(company.isPresent())
        {
             return Optional.of(CompanyMapper.mapToCompanyDto(company.get()));
        }
        return Optional.empty();
    }



}
