package com.ryuu.movieReservation.service.reservation;

import com.ryuu.movieReservation.dto.ReservationDto;

public interface ReservationService {
    ReservationDto reserveSeats(Long showtimeId, int numOfSeats, Long userId);
}
