package com.ryuu.movieReservation.dto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private UserReservationDto user;
    private ShowtimeDto showtime;
    private int numOfSeatsBooked;
    private BigDecimal total_price;
    private LocalDateTime bookingDate;
}
