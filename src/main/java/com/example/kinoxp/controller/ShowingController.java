package com.example.kinoxp.controller;

import com.example.kinoxp.model.Showing;
import com.example.kinoxp.service.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/api/showing")
@RequestMapping("/api/showings")
@RestController
public class ShowingController {

    private ShowingService showingService;

    public ShowingController(ShowingService showingService){
        this.showingService = showingService;
    }

    @GetMapping()
    public ResponseEntity<List<Showing>> getShowings(){

        return ResponseEntity.ok(showingService.getShowings());
    }

}
