package com.ryuu.movieReservation.dto;

import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.Role;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReservationDto {
    private String id;
    private String email;
}
