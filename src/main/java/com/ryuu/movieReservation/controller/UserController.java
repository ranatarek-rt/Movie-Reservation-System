package com.ryuu.movieReservation.controller;

import com.ryuu.movieReservation.dto.UserDto;
import com.ryuu.movieReservation.dto.UserRequestDto;
import com.ryuu.movieReservation.model.User;
import com.ryuu.movieReservation.response.ApiResponse;
import com.ryuu.movieReservation.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){
        UserDto user = userService.registerUser(userRequestDto);
        return ResponseEntity.ok(new ApiResponse("the user is registered successfully",user));
    }
}
