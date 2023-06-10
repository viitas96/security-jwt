package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableBooking, Long> {
}
