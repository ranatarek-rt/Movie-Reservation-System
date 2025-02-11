package com.ryuu.movieReservation.dto;

import com.ryuu.movieReservation.model.Role;
import lombok.*;

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
}
