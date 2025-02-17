package com.ryuu.movieReservation.service.reservation;

import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.UserReservationResponseDto;
import com.ryuu.movieReservation.exception.NoEnoughSeatsException;
import com.ryuu.movieReservation.exception.ShowtimeNotFoundException;
import com.ryuu.movieReservation.exception.UserNotFoundException;
import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.Showtime;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.ReservationRepo;
import com.ryuu.movieReservation.repository.ShowtimeRepo;
import com.ryuu.movieReservation.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    ReservationRepo reservationRepo;
    ShowtimeRepo showtimeRepo;
    UserRepo userRepo;
    ModelMapper modelMapper;
    @Autowired
    ReservationServiceImpl(ReservationRepo reservationRepo,ShowtimeRepo showtimeRepo,UserRepo userRepo,ModelMapper modelMapper){
        this.reservationRepo = reservationRepo;
        this.showtimeRepo = showtimeRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservationDto reserveSeats(Long showtimeId, int numOfSeats, Long userId) {
        Showtime showtime = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new ShowtimeNotFoundException("Showtime not found"));

        User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("there is no user found with that id"));
        if (showtime.getAvailableSeats() < numOfSeats) {
            throw new NoEnoughSeatsException("Not enough available seats!");
        }

        Reservation reservation = new Reservation();
        reservation.setShowtime(showtime);
        reservation.setUser(user);
        reservation.setNumOfSeatsBooked(numOfSeats);
        reservation.setBookingDate(LocalDateTime.now());
        reservationRepo.save(reservation);
        showtime.setAvailableSeats(showtime.getAvailableSeats() - numOfSeats);
        showtimeRepo.save(showtime);

        return modelMapper.map(reservation,ReservationDto.class);
    }



    @Override
    public List<UserReservationResponseDto> getUserReservations(Long userId){
        User user  = userRepo
                .findById(userId)
                .orElseThrow(()->new UserNotFoundException("there is no user found with that Id"));

        List<Reservation> reservations = reservationRepo.findReservationsByUser(user);
        return reservations
                .stream()
                .map(reservation-> modelMapper.map(reservation,UserReservationResponseDto.class))
                .toList();
    }













}
