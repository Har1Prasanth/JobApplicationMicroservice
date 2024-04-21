package com.irah.companyms.company.repository;

import com.irah.companyms.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}
