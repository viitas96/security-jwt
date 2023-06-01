package dev.betisor.securityjwt.repository;

import dev.betisor.securityjwt.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathDAO extends JpaRepository<Path, Long> {
}
