package com.ryuu.movieReservation.exception;

public class NoEnoughSeatsException extends RuntimeException{
    public NoEnoughSeatsException(String message){
        super(message);
    }

}
