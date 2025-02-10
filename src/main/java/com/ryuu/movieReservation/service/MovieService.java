package com.ryuu.movieReservation.service;

import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.dto.MovieUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieRequestDto movieRequestDto);

    String uploadPoster(Long movieId, MultipartFile file) throws IOException;

    MovieDto getMovieById(Long id);
    List<MovieDto> getAllMovies();
    void deleteMovieById(Long id);
    MovieDto updateMovie(Long id, MovieUpdateDto movieUpdateDto);

    List<MovieDto> searchByGenre(String genre);
}
