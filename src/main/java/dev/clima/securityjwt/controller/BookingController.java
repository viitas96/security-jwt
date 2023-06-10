package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.CreateBookingDTO;
import dev.clima.securityjwt.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        var response = bookingService.getBookings(date);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{tableId}")
    public ResponseEntity<Void> createBooking(@RequestBody CreateBookingDTO dto,
                                              @PathVariable Long tableId) {
        bookingService.createBooking(dto, tableId);
        return ResponseEntity.noContent().build();
    }

}
