package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
