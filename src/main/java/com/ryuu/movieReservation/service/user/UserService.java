package com.ryuu.movieReservation.service.user;

import com.ryuu.movieReservation.dto.UserDto;
import com.ryuu.movieReservation.dto.UserRequestDto;

public interface UserService {

    UserDto registerUser(UserRequestDto userRequestDto);
}
