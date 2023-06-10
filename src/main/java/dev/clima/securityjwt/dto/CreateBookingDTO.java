package dev.clima.securityjwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateBookingDTO {

    private LocalDateTime localDateTime;
}
