package com.example.kinoxp.service;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){

        this.movieRepository = movieRepository;

    }

    public ResponseEntity<List<Movie>> getAllMovies() {

        return null;

    }
}
