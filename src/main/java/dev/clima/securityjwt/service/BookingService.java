package dev.clima.securityjwt.service;

import dev.clima.securityjwt.dto.BookingDTO;
import dev.clima.securityjwt.dto.CreateBookingDTO;
import dev.clima.securityjwt.entity.Booking;
import dev.clima.securityjwt.repository.BookingRepository;
import dev.clima.securityjwt.repository.TableRepository;
import dev.clima.securityjwt.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TableRepository tableRepository;
    private final UserDAO userDAO;


    public Map<Long, List<BookingDTO>> getBookings(LocalDate date) {
        var startDate = date.atStartOfDay();
        var endDate = date.atTime(23,59);
        var bookings = bookingRepository.findAllByDateTimeBetween(startDate, endDate).stream();
        Map<Long, List<BookingDTO>> map = new HashMap<>();

        bookings.forEach(booking ->
            map.computeIfAbsent(booking.getTableBooking().getId(), k -> new ArrayList<>()).add(new BookingDTO(booking))
        );

        return map;
    }

    public void createBooking(CreateBookingDTO dto, Long tableId) {
        if (bookingRepository.existsActiveBooking(dto.getLocalDateTime().minusMinutes(30L),
                dto.getLocalDateTime().plusMinutes(30L),
                tableId)) {
            throw new RuntimeException();
        }
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userDAO.findByEmail(email).orElseThrow(() -> new RuntimeException());
        var table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException());
        Booking booking = new Booking(user, table, dto.getLocalDateTime());
        bookingRepository.save(booking);
    }
}
