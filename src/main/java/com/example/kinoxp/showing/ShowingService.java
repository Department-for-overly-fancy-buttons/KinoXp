package com.example.kinoxp.showing;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.movie.Movie;
import com.example.kinoxp.movie.MovieService;
import com.example.kinoxp.theater.Theater;
import com.example.kinoxp.theater.TheaterService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowingService {

    private final ShowingRepository showingRepository;
    private final MovieService movieService;
    private final TheaterService theaterService;


    ShowingService(ShowingRepository showingRepository, MovieService movieService, TheaterService theaterService) {
        this.showingRepository = showingRepository;
        this.movieService = movieService;
        this.theaterService = theaterService;
    }

    public Showing createShowing(CreateShowingRequest request) {
        Showing showing = new Showing();
        Movie movie = movieService.getMovieById(request.movieId());
        Theater theater = theaterService.getTheaterById(request.theaterId());
        showing.setMovie(movie);
        showing.setTheater(theater);
        showing.setStartTime(request.startTime());
        showing.setThreeDimensional(request.is3d());
        return showingRepository.save(showing);
    }

    List<Showing> getShowings() {
        return showingRepository.findAllByStartTimeAfterOrderByStartTime(LocalDateTime.now());
    }

    public Showing getShowingById(Long id) {
        return showingRepository.findById(id).orElseThrow(() -> new NotFoundException("Showing not found with id: " + id));
    }

    public List<Showing> getShowingsForTheater(Long theaterId) {
        return showingRepository.findByTheater_IdAndStartTimeAfterOrderByStartTime(theaterId, LocalDateTime.now());
    }

    List<Showing> getShowingsForMovie(Long movieId) {
        return showingRepository.findByMovie_Id(movieId);
    }

    void deleteShowingById(Long id) {
        showingRepository.deleteById(id);
    }


}
