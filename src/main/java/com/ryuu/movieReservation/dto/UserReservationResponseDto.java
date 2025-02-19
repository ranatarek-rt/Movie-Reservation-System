package com.ryuu.movieReservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReservationResponseDto {
    private Long id;
    private ShowtimeResponseDto showtime;
    private int numOfSeatsBooked;
    private LocalDateTime bookingDate;
}
