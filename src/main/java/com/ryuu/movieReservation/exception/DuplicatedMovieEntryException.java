package com.ryuu.movieReservation.exception;

public class DuplicatedMovieEntryException extends RuntimeException{

    public DuplicatedMovieEntryException(String message){
        super(message);
    }
}
