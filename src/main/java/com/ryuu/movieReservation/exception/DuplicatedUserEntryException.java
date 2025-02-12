package com.ryuu.movieReservation.exception;

public class DuplicatedUserEntryException extends RuntimeException{
    public DuplicatedUserEntryException(String message){
        super(message);
    }
}
