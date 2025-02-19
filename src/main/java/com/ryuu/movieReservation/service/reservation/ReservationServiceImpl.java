package com.ryuu.movieReservation.service.reservation;

import com.ryuu.movieReservation.dto.AdminDetailsDto;
import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.RevenueDto;
import com.ryuu.movieReservation.dto.UserReservationResponseDto;
import com.ryuu.movieReservation.exception.*;
import com.ryuu.movieReservation.exception.IllegalStateException;
import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.Showtime;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.ReservationRepo;
import com.ryuu.movieReservation.repository.ShowtimeRepo;
import com.ryuu.movieReservation.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Transactional
    @Override
    public ReservationDto reserveSeats(Long showtimeId, int numOfSeats, Long userId) {
        Showtime showtime = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new ShowtimeNotFoundException("Showtime not found"));

        User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("there is no user found with that id"));
        if (showtime.getAvailableSeats() < numOfSeats) {
            throw new NoEnoughSeatsException("Not enough available seats!");
        }

        BigDecimal totalPrice = BigDecimal.valueOf(numOfSeats).multiply(showtime.getSeatPrice());

        Reservation reservation = new Reservation();
        reservation.setShowtime(showtime);
        reservation.setUser(user);
        reservation.setNumOfSeatsBooked(numOfSeats);
        reservation.setBookingDate(LocalDateTime.now());
        reservation.setTotal_price(totalPrice);
        reservationRepo.save(reservation);
        showtime.setAvailableSeats(showtime.getAvailableSeats() - numOfSeats);
        showtimeRepo.save(showtime);

        return modelMapper.map(reservation,ReservationDto.class);
    }

    @Override
    public List<ReservationDto> getAllReservations(){
        List<Reservation> reservations = reservationRepo.findAll();
        return reservations
                .stream()
                .map(reservation -> modelMapper.map(reservation,ReservationDto.class))
                .toList();
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

    @Transactional
    @Override
    public void cancelReservation(Long userId, Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        if (!reservation.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only cancel your own reservations.");
        }

        if (reservation.getShowtime().getShowTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("You can only cancel upcoming reservations.");
        }

        Showtime showtime = reservation.getShowtime();
        showtime.setAvailableSeats(showtime.getAvailableSeats() + reservation.getNumOfSeatsBooked());
        reservationRepo.delete(reservation);
    }

    @Override
    public RevenueDto getRevenue(){

        List<Reservation> reservations = reservationRepo.findAll();

        BigDecimal totalRevenue = reservations.stream()
                .map(Reservation::getTotal_price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RevenueDto(totalRevenue);
    }


    @Override
    public AdminDetailsDto getAllDetails(){
        List<Reservation> reservations  = reservationRepo.findAll();
        List<Showtime> showtimeList = showtimeRepo.findAll();

        int totalCap = showtimeList
                .stream()
                .mapToInt(showtime->showtime.getAvailableSeats() + showtime.getReservations().stream()
                .mapToInt(Reservation::getNumOfSeatsBooked).sum())
                .sum();

        int remainingCapacity = showtimeList
                .stream()
                .mapToInt(Showtime::getAvailableSeats)
                .sum();


        BigDecimal totalRevenue = reservations.stream()
                .map(Reservation::getTotal_price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AdminDetailsDto(totalCap,remainingCapacity,totalRevenue);
    }



}
