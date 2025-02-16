package com.ryuu.movieReservation.repository;

import com.ryuu.movieReservation.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ShowtimeRepo extends JpaRepository<Showtime,Long> {

}
