package com.ryuu.movieReservation.security;

import com.ryuu.movieReservation.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsMapper {


    public UserDetails toUserDetails(User user) {
        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

}
