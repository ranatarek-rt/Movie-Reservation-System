package com.ryuu.movieReservation.exception;

import com.ryuu.movieReservation.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(DuplicatedMovieEntryException.class)
    public ResponseEntity<ApiResponse> duplicatedMovieExceptionHandler(DuplicatedMovieEntryException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ApiResponse> movieNotFoundExceptionHandler(MovieNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicatedUserEntryException.class)
    public ResponseEntity<ApiResponse> duplicatedUserExceptionHandler(DuplicatedUserEntryException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<ApiResponse> showtimeNotfoundExceptionHandler(ShowtimeNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.CONFLICT);
    }

}
