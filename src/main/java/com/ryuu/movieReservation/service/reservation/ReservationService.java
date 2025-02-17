package com.ryuu.movieReservation.service.reservation;

import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.UserReservationResponseDto;

import java.util.List;

public interface ReservationService {
    ReservationDto reserveSeats(Long showtimeId, int numOfSeats, Long userId);

    List<UserReservationResponseDto> getUserReservations(Long userId);
}
