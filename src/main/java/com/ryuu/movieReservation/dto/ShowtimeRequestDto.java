package com.ryuu.movieReservation.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeRequestDto {
    private Long id;
    private LocalDateTime showTime;
    private int availableSeats;
    private BigDecimal seatPrice;
}
