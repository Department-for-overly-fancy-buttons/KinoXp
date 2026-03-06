package com.example.kinoxp.service;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repository.CategoryRepository;
import com.example.kinoxp.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository){

        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;

    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();

    }

    public void createMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
