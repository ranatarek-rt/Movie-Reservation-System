package com.ryuu.movieReservation.service.reservation;

import com.ryuu.movieReservation.dto.ReservationDto;
import com.ryuu.movieReservation.dto.UserReservationResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ReservationService {
    ReservationDto reserveSeats(Long showtimeId, int numOfSeats, Long userId);

    List<ReservationDto> getAllReservations();

    List<UserReservationResponseDto> getUserReservations(Long userId);

    @Transactional
    void cancelReservation(Long userId, Long reservationId);
}
