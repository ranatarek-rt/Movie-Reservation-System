package com.ryuu.movieReservation.controller;

import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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




}
