package com.irah.companyms.company.controller;

import com.irah.companyms.company.model.CompanyDto;
import com.irah.companyms.company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;



    //Get All jobs
    @GetMapping
    public ResponseEntity<List<CompanyDto>> findAllJobs() {

        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateJob(@PathVariable("id") Long companyId, @RequestBody CompanyDto companyDto){
        Optional<CompanyDto> companyDto1=companyService.updateCompany(companyId,companyDto);
        if(companyDto1.isPresent())
            return ResponseEntity.ok(companyDto1.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody CompanyDto companyDto) {
        companyService.addCompany(companyDto);
        return ResponseEntity.ok("Company Added Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Long companyId){

        boolean isDeleted = companyService.deleteCompanyById(companyId);
        if(isDeleted){
            return ResponseEntity.ok("Company with ID "+companyId+ " Deleted Successfully");
        }
        return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);

    }

    //Find By ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> findCompanyById(@PathVariable("id") Long companyId){
        Optional<CompanyDto> companyDto=companyService.findCompanyById(companyId );
        if(companyDto.isPresent())
            return ResponseEntity.ok(companyDto.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
