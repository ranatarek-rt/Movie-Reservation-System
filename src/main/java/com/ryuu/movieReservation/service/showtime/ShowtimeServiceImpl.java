package com.ryuu.movieReservation.service.showtime;

import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.Showtime;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.ReservationRepo;
import com.ryuu.movieReservation.repository.ShowtimeRepo;
import com.ryuu.movieReservation.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShowtimeServiceImpl implements ShowtimeService{
    ShowtimeRepo showtimeRepo;
    UserRepo userRepo;
    ReservationRepo reservationRepo;
    @Autowired
    ShowtimeServiceImpl (ShowtimeRepo showtimeRepo,UserRepo userRepo,ReservationRepo reservationRepo){
        this.showtimeRepo = showtimeRepo;
        this.userRepo =userRepo;
        this.reservationRepo = reservationRepo;
    }

//    public void bookSeats(Long userId, Long showtimeId, int numSeatsToBook) {
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Showtime showtime = showtimeRepo.findById(showtimeId)
//                .orElseThrow(() -> new RuntimeException("Showtime not found"));
//        if (showtime.getAvailableSeats() < numSeatsToBook) {
//            throw new RuntimeException("Not enough seats available for this showtime");
//        }
//        Reservation reservation = new Reservation();
//        reservation.setUser(user);
//        reservation.setShowtime(showtime);
//        reservation.setBookingDate(LocalDateTime.now());
//        reservationRepo.save(reservation);
//        showtime.setAvailableSeats(showtime.getAvailableSeats() - numSeatsToBook);
//        showtimeRepo.save(showtime);
//    }
//








}
