package com.ryuu.movieReservation.controller;


import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    MovieService movieService;

    @Autowired
    MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createMovie(@Valid @RequestBody MovieRequestDto movieRequestDto){
        MovieDto movie = movieService.createMovie(movieRequestDto);
        return ResponseEntity.ok(new ApiResponse("the movie is created successfully",movie));
    }
}
