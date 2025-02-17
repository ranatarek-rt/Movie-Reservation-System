package com.ryuu.movieReservation.controller;

import com.ryuu.movieReservation.dto.ShowtimeRequestDto;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.showtime.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/showtime")
public class ShowtimeController {

    ShowtimeService showtimeService;

    @Autowired
    ShowtimeController(ShowtimeService showtimeService){
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllShowTime(){
        List<ShowtimeRequestDto> showtimeList = showtimeService.getAllShowTimes();
        return ResponseEntity.ok(new ApiResponse("All show times are fetched successfully",showtimeList));
    }


    @GetMapping("/{showtimeId}")
    public ResponseEntity<ApiResponse> getShowtimeById(@PathVariable Long showtimeId){
        ShowtimeRequestDto showtime = showtimeService.getShowTimeBydId(showtimeId);
        return ResponseEntity.ok(new ApiResponse("the showtime is fetched successfully",showtime));
    }
    @GetMapping("/{showtimeId}/seats")
    public ResponseEntity<ApiResponse> getAvailableSeats(@PathVariable Long showtimeId){
        int seats = showtimeService.getAvailableSeats(showtimeId);
        return ResponseEntity.ok(new ApiResponse("the number of available seats for this showtime are",seats));
    }



}
