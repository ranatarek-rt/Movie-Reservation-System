package com.ryuu.movieReservation.repository;

import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
    List<Reservation> findReservationsByUser(User User);

}
