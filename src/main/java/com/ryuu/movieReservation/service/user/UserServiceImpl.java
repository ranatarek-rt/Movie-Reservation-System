package com.ryuu.movieReservation.service.user;

import com.ryuu.movieReservation.dto.UserDto;
import com.ryuu.movieReservation.dto.UserRequestDto;
import com.ryuu.movieReservation.exception.DuplicatedMovieEntryException;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    UserServiceImpl(UserRepo userRepo,ModelMapper modelMapper){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto registerUser(UserRequestDto userRequestDto) {
        if(userRepo.findUserByEmail(userRequestDto.getEmail()).isPresent()){
            throw new DuplicatedMovieEntryException("there is already a user exist with that email please try to Log in");
        }
        User user = userRepo.save(modelMapper.map(userRequestDto,User.class));
        return modelMapper.map(user,UserDto.class);
    }
}
