package com.irah.companyms.company.mapper;

import com.irah.companyms.company.entity.Company;
import com.irah.companyms.company.model.CompanyDto;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {

    public static CompanyDto mapToCompanyDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company,companyDto);
        return companyDto;
    }

    public static Company mapToCompany(CompanyDto companyDto){
        Company company = new Company();
        BeanUtils.copyProperties(companyDto,company);
        return company;
    }

}
