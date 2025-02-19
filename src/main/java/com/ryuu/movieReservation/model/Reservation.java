package com.ryuu.movieReservation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    @JsonIgnore
    private Showtime showtime;

    @Column(name="num_of_seats_booked")
    private int numOfSeatsBooked;

    @Column(name="total_price")
    private double total_price;

    @Column(name="booking_date", nullable = false)
    private LocalDateTime bookingDate;
}