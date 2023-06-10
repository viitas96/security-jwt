package dev.clima.securityjwt.repository;

import dev.clima.securityjwt.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
            select count(booking) > 0
            from Booking booking
            where booking.tableBooking.id = ?3
            and booking.dateTime between ?1 and ?2
            """)
    boolean existsActiveBooking(LocalDateTime startDate, LocalDateTime endTime, Long tableId);

}
