package com.ryuu.movieReservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeResponseDto {
    private LocalDateTime showTime;
}
