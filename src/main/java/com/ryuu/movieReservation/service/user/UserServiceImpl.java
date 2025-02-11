package com.ryuu.movieReservation.service.user;

import com.ryuu.movieReservation.dto.UserDto;
import com.ryuu.movieReservation.dto.UserRequestDto;
import com.ryuu.movieReservation.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    UserRepo userRepo;

    @Autowired
    UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDto registerUser(UserRequestDto userRequestDto) {
        return null;
    }
}
