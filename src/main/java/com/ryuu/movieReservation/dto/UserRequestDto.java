package com.ryuu.movieReservation.dto;

import com.ryuu.movieReservation.model.Reservation;
import com.ryuu.movieReservation.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {
    @NotBlank(message = "the email is required")
    private String email;
    @NotBlank(message = "the password is required")
    private String password;
    @NotNull(message = "The role is required")
    private Role role;
    private List<Reservation> reservations;
}
