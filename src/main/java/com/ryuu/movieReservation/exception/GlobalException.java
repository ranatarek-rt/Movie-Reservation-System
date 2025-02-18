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
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedUserEntryException.class)
    public ResponseEntity<ApiResponse> duplicatedUserExceptionHandler(DuplicatedUserEntryException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<ApiResponse> showtimeNotfoundExceptionHandler(ShowtimeNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> userNotFoundExceptionHandler(UserNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoEnoughSeatsException.class)
    public ResponseEntity<ApiResponse> noEnoughSeatsExceptionHandler(NoEnoughSeatsException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ApiResponse> reservationNotFoundExceptionHandler(ReservationNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> unauthorizedExceptionHandler(UnauthorizedException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> illegalStateExceptionHandler(IllegalStateException ex){
        return new ResponseEntity<>(new ApiResponse(ex.getMessage() , null), HttpStatus.BAD_REQUEST);
    }



}
