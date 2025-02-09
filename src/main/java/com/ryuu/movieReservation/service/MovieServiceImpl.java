package com.ryuu.movieReservation.service;


import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.exception.DuplicatedMovieEntryException;
import com.ryuu.movieReservation.exception.MovieNotFoundException;
import com.ryuu.movieReservation.model.Movie;
import com.ryuu.movieReservation.repository.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService{

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/uploads/";

    MovieRepo movieRepo;
    ModelMapper modelMapper;

    @Autowired
    MovieServiceImpl(MovieRepo movieRepo, ModelMapper modelMapper){
        this.movieRepo = movieRepo;
        this.modelMapper= modelMapper;

    }

    //this will be the responsibility for the admin to create a new movie
    @Override
    public MovieDto createMovie(MovieRequestDto movieRequestDto) {
        if(movieRepo.findMovieByTitleAndReleaseYear(movieRequestDto.getTitle(),movieRequestDto.getReleaseYear()).isPresent()) {
            throw new DuplicatedMovieEntryException("there is a movie exits with the same name and release year ");
        }
        Movie movie = modelMapper.map(movieRequestDto,Movie.class);
        if (movie.getPoster() == null || movie.getPoster().isEmpty()) {
            movie.setPoster("");
        }
        Movie finalMovie = movieRepo.save(movie);
        return modelMapper.map(finalMovie,MovieDto.class);
    }


    @Override
    public String uploadPoster(Long movieId, MultipartFile file) throws IOException {
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        fileName = fileName.replaceAll("\\s", "_");
        Path path = Paths.get(IMAGE_DIRECTORY + "/" + fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        movie.setPoster("/images/" + fileName);
        movieRepo.save(movie);
        return "/images/" + fileName;
    }

}
