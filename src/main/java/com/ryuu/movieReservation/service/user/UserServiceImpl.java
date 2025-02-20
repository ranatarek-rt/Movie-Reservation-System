package com.ryuu.movieReservation.service.user;

import com.ryuu.movieReservation.dto.UserDto;
import com.ryuu.movieReservation.dto.UserRequestDto;
import com.ryuu.movieReservation.exception.DuplicatedMovieEntryException;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    UserRepo userRepo;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepo userRepo,ModelMapper modelMapper,PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserRequestDto userRequestDto) {
        if(userRepo.findUserByEmail(userRequestDto.getEmail()).isPresent()){
            throw new DuplicatedMovieEntryException("there is already a user exist with that email please try to Log in");
        }
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User user = userRepo.save(modelMapper.map(userRequestDto,User.class));
        return modelMapper.map(user,UserDto.class);
    }
}
