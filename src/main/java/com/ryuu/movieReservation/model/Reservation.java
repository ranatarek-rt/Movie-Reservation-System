package com.ryuu.movieReservation.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @Column(name="num_of_seats_booked")
    private int numOfSeatsBooked;

    @Column(name="booking_date")
    private LocalDateTime bookingDate;


}
