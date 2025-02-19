package com.ryuu.movieReservation.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDetailsDto {
    private int totalCapacity;
    private int remainingCapacity;
    private BigDecimal totalRevenue;
}
