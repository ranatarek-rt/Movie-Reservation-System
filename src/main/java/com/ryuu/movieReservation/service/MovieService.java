package com.ryuu.movieReservation.service;

import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;

public interface MovieService {
    MovieDto createMovie(MovieRequestDto movieRequestDto);

}
