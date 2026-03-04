package com.example.kinoxp.controller;

import com.example.kinoxp.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovieControllerInterface {

    ResponseEntity<List<Movie>> getMovies();

    void addMovie(@RequestBody Movie movie);

}
