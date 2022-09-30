package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {
}
