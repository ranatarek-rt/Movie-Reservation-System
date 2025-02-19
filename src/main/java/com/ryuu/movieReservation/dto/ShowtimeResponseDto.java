package com.ryuu.movieReservation.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeResponseDto {
    private LocalDateTime showTime;
    private BigDecimal seatPrice;
}
