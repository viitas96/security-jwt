package dev.clima.securityjwt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private TableBooking tableBooking;

    private LocalDateTime dateTime;

    public Booking(User user, TableBooking tableBooking, LocalDateTime dateTime) {
        this.user = user;
        this.tableBooking = tableBooking;
        this.dateTime = dateTime;
    }
}
