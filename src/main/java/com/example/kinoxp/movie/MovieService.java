package com.example.kinoxp.movie;

import com.example.kinoxp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {

        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;

    }

    List<Movie> getAllMovies() {

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

    void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}
