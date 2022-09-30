package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.CompanyDTO;
import dev.clima.securityjwt.entity.Company;
import dev.clima.securityjwt.service.CompanyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/companies")
public class CompanyController {

    private CompanyService companyService;

    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public CompanyDTO getCompany(@PathVariable long id) {
        return modelMapper.map(companyService.getById(id), CompanyDTO.class);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAll();
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

}
