package com.ryuu.movieReservation.exception;


public class ShowtimeNotFoundException extends RuntimeException {
    public ShowtimeNotFoundException(String message){
        super(message);
    }
}
