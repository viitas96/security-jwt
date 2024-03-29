package dev.clima.securityjwt.service;

import dev.clima.securityjwt.dto.CompanyDTO;
import dev.clima.securityjwt.entity.Company;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.CompanyDAO;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final UserDAO userDAO;

    private final CompanyDAO companyDAO;

    private final ModelMapper modelMapper;

    public CompanyDTO getById(Long id) {
        return modelMapper.map(companyDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found!")),
                CompanyDTO.class);
    }

    public void addUser(long companyId, long userId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setCompany(companyDAO.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company not found")));
        userDAO.save(user);
    }

    public List<CompanyDTO> getAll() {
        return companyDAO.findAll().stream().map(e -> modelMapper.map(e, CompanyDTO.class)).toList();
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company company = companyDAO.save(modelMapper.map(dto, Company.class));
        return modelMapper.map(company, CompanyDTO.class);
    }

    public CompanyDTO updateCompany(long companyId, CompanyDTO dto) {
        Company company = companyDAO.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        company.setName(dto.getName());
        return modelMapper.map(companyDAO.save(company), CompanyDTO.class);
    }

    public void delete(Long id) {
        var company = companyDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        companyDAO.delete(company);
    }

}
