package dev.clima.securityjwt.service;

import dev.clima.securityjwt.entity.Company;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.CompanyDAO;
import dev.clima.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final UserDAO userDAO;

    private final CompanyDAO companyDAO;

    public Company getById(Long id) {
        return companyDAO.findById(id).orElseThrow(null);
    }

    public void addUser(long companyId, long userId) {
        User user = userDAO.findById(userId).orElseThrow(null);
        user.setCompany(getById(companyId));
        userDAO.save(user);
    }

    public List<Company> getAll() {
        return companyDAO.findAll();
    }

    public Company save(Company company) {
        return companyDAO.save(company);
    }

    public void delete(Long id) {
        companyDAO.deleteById(id);
    }

}
