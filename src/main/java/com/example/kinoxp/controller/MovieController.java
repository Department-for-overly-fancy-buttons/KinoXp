package com.example.kinoxp.controller;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController{

    private final MovieService movieService;

    public MovieController(MovieService movieService){

        this.movieService = movieService;

    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
