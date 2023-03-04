package dev.clima.securityjwt.service;

import dev.clima.securityjwt.dto.CompanyDTO;
import dev.clima.securityjwt.entity.Company;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.CompanyDAO;
import dev.clima.securityjwt.repository.UserDAO;
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
        return modelMapper.map(companyDAO.findById(id).orElseThrow(() -> new RuntimeException("Company not found!")),
                CompanyDTO.class);
    }

    public void addUser(long companyId, long userId) {
        User user = userDAO.findById(userId).orElseThrow(null);
        user.setCompany(companyDAO.findById(companyId).orElseThrow(null));
        userDAO.save(user);
    }

    public List<CompanyDTO> getAll() {
        return companyDAO.findAll().stream().map(e -> modelMapper.map(e, CompanyDTO.class)).toList();
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company company = companyDAO.save(modelMapper.map(dto, Company.class));
        return modelMapper.map(company, CompanyDTO.class);
    }

    public void delete(Long id) {
        companyDAO.deleteById(id);
    }

}
