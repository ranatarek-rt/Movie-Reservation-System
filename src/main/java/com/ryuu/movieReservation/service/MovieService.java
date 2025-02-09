package com.ryuu.movieReservation.service;

import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MovieService {
    MovieDto createMovie(MovieRequestDto movieRequestDto);

    String uploadPoster(Long movieId, MultipartFile file) throws IOException;
}
