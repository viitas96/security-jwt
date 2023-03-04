package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathDAO extends JpaRepository<Path, Long> {
}
