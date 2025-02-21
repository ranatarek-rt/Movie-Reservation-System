package com.ryuu.movieReservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeRedirect {

    @GetMapping("/")
    public String redirectHomePageToSwagger(){
        return "redirect:/swagger-ui/index.html";
    }
}
