package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeDAO extends JpaRepository<Privilege, Long> {
}
