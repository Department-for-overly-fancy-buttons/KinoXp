package com.example.kinoxp.controller;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")

@RestController
public class MovieController implements MovieControllerInterface {

    private final MovieService movieService;

    public MovieController(MovieService movieService){

        this.movieService = movieService;

    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies(){
        return movieService.getAllMovies();
    }

    public void addMovie(@RequestBody Movie movie){

    }

}
