package com.ryuu.movieReservation.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RevenueDto {
    private BigDecimal totalRevenue;
}
