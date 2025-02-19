package com.ryuu.movieReservation.controller;
import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.RevenueDto;
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

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReservations(){
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(new ApiResponse("All reservations list", reservations));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserReservations(@PathVariable Long userId){
        List<UserReservationResponseDto> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(new ApiResponse("The user reservations list is fetched successfully", reservations));
    }

    @DeleteMapping("/user/cancel")
    public ResponseEntity<ApiResponse> cancelUserReservation(@RequestParam Long userId , @RequestParam Long reservationId){
        reservationService.cancelReservation(userId,reservationId);
        return ResponseEntity.ok(new ApiResponse("The reservation is canceled successfully", null));

    }

    @GetMapping("/total/revenue")
    public ResponseEntity<ApiResponse> totalRevenue(){
        RevenueDto revenueDto = reservationService.getRevenue();
        return ResponseEntity.ok(new ApiResponse("The Total Revenue", revenueDto));

    }

}
