package com.ryuu.movieReservation.controller;


import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.dto.MovieUpdateDto;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.movie.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


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

    @PostMapping("/{movieId}/poster")
    public ResponseEntity<ApiResponse> uploadMoviePoster(@PathVariable Long movieId, @RequestParam("file") MultipartFile file) throws IOException {
        String posterUrl = movieService.uploadPoster(movieId, file);
        posterUrl = posterUrl.replaceAll("\\s", "_");
        return ResponseEntity.ok(new ApiResponse("Poster uploaded successfully", posterUrl));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovies(){
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(new ApiResponse("All movies are fetched successfully ",movies));
    }

    @GetMapping("{movieId}")
    public ResponseEntity<ApiResponse> getMovieById(@PathVariable Long movieId){
        MovieDto movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(new ApiResponse("the movie with id "+ movieId +" is fetched successfully",movie));
    }

    @DeleteMapping("{movieId}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long movieId){
        movieService.deleteMovieById(movieId);
        return ResponseEntity.ok(new ApiResponse("the movie is deleted successfully",null));
    }

    @PatchMapping("{movieId}/update")
    public ResponseEntity<ApiResponse> updateMovieFields(@PathVariable Long movieId, @RequestBody MovieUpdateDto movieUpdateDto){
        MovieDto updatedMovie = movieService.updateMovie(movieId,movieUpdateDto);
        return ResponseEntity.ok(new ApiResponse("the movie is successfully updated",updatedMovie));
    }

    @GetMapping("/search/{genre}")
    public ResponseEntity<ApiResponse> searchByGenre(@PathVariable String genre){
        List<MovieDto> movies = movieService.searchByGenre(genre);
        return ResponseEntity.ok(new ApiResponse("fetched all movies under same genre",movies));
    }


    @GetMapping("/by-date")
    public ResponseEntity<ApiResponse> getMoviesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<MovieDto> movies = movieService.getMoviesByDate(date);
        return ResponseEntity.ok(new ApiResponse("fetched all movies under the required date "+ date,movies));
    }
}
