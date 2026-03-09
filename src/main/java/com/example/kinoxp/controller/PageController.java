package com.example.kinoxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/{movieId}")
    public String showReseravtionForm(@RequestParam long movieId){
        System.out.println(movieId);
        return "Reservation";
    }
}
