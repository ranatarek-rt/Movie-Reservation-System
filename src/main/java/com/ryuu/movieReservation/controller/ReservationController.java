package com.ryuu.movieReservation.controller;

import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.UserReservationResponseDto;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/reserve")
public class ReservationController {
    ReservationService reservationService;

    @Autowired
    ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> reserveSeats(
            @RequestParam Long showtimeId , @RequestParam int numOfSeats , @RequestParam Long userId){

        ReservationDto reservation = reservationService.reserveSeats(showtimeId,numOfSeats,userId);

        return ResponseEntity.ok(new ApiResponse("the user reserved seats successfully", reservation));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserReservations(@PathVariable Long userId){
        List<UserReservationResponseDto> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(new ApiResponse("The user reservations list is fetched successfully", reservations));
    }


}
