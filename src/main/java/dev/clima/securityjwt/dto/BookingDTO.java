package dev.clima.securityjwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.clima.securityjwt.entity.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
    private String username;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    private LocalDateTime dateTime;

    public BookingDTO(Booking booking) {
        this.username = booking.getUser().getNickName();
        this.dateTime = booking.getDateTime();
    }

}
