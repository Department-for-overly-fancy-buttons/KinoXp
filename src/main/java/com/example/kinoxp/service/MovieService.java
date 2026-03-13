package com.example.kinoxp.service;

import com.example.kinoxp.dto.CreateMovieRequest;
import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Category;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repository.CategoryRepository;
import com.example.kinoxp.repository.MovieRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {

        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;

    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();

    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    public Movie createMovie(CreateMovieRequest request) {
        Movie movie = new Movie(request.title(), request.description(), request.pgRating(), request.duration(), request.categories());
        movie.setPoster(request.posterData());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}
