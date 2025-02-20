package com.ryuu.movieReservation.service.userdetails;

import com.ryuu.movieReservation.exception.UserNotFoundException;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.repository.UserRepo;
import com.ryuu.movieReservation.security.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepo userRepo;
    UserDetailsMapper userDetailsMapper;
    @Autowired
    CustomUserDetailsService(UserRepo userRepo,UserDetailsMapper userDetailsMapper){
        this.userRepo = userRepo;
        this.userDetailsMapper = userDetailsMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo
                .findUserByEmail(email)
                .orElseThrow(()->new UserNotFoundException("there is no user found with that email"));
        return userDetailsMapper.toUserDetails(user);
    }
}
