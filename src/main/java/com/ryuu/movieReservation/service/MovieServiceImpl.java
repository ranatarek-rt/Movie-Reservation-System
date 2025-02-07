package com.ryuu.movieReservation.service;


import com.ryuu.movieReservation.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

    MovieRepo movieRpo;

    @Autowired
    MovieServiceImpl(MovieRepo movieRpo){
        this.movieRpo = movieRpo;
    }

}
