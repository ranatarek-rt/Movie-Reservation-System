package com.ryuu.movieReservation.service;


import com.ryuu.movieReservation.dto.MovieDto;
import com.ryuu.movieReservation.dto.MovieRequestDto;
import com.ryuu.movieReservation.exception.DuplicatedMovieEntryException;
import com.ryuu.movieReservation.model.Movie;
import com.ryuu.movieReservation.repository.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepo movieRepo;
    MovieRepo movieRpo;
    ModelMapper modelMapper;

    @Autowired
    MovieServiceImpl(MovieRepo movieRpo, ModelMapper modelMapper, MovieRepo movieRepo){
        this.movieRpo = movieRpo;
        this.modelMapper= modelMapper;
        this.movieRepo = movieRepo;
    }

    //this will be the responsibility for the admin to create a new movie
    @Override
    public MovieDto createMovie(MovieRequestDto movieRequestDto) {
        if(movieRepo.findMovieByTitleAndReleaseYear(movieRequestDto.getTitle(),movieRequestDto.getReleaseYear()).isPresent()) {
            throw new DuplicatedMovieEntryException("there is a movie exits with the same name and release year ");
        }
        Movie movie = movieRpo.save(modelMapper.map(movieRequestDto,Movie.class));
        return modelMapper.map(movie,MovieDto.class);
    }
}
