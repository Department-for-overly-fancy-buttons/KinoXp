package com.example.kinoxp.controller;

import com.example.kinoxp.model.Showing;
import com.example.kinoxp.service.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/showings")
@Controller
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
