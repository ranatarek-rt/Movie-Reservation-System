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
public class UserDto {
    private String id;
    private String email;
    private String password;
    private Role role;
    private List<Reservation> reservations;
}
