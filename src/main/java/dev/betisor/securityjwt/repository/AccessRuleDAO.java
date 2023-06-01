package dev.betisor.securityjwt.repository;

import dev.betisor.securityjwt.entity.AccessRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRuleDAO extends JpaRepository<AccessRule, Long> {
}
