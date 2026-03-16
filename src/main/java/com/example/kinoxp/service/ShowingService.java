package com.example.kinoxp.service;

import com.example.kinoxp.dto.CreateShowingRequest;
import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Showing;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.repository.MovieRepository;
import com.example.kinoxp.repository.ShowingRepository;
import com.example.kinoxp.repository.TheaterRepository;
import org.springframework.boot.actuate.endpoint.Show;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowingService {

    private final ShowingRepository showingRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;


    public ShowingService(ShowingRepository showingRepository, MovieRepository movieRepository, TheaterRepository theaterRepository) {
        this.showingRepository = showingRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    public Showing createShowing(CreateShowingRequest request) {
        Showing showing = new Showing();
        Optional<Movie> movieOptional = movieRepository.findById(request.movieId());
        Optional<Theater> theaterOptional = theaterRepository.findById(request.theaterId());
        if (movieOptional.isEmpty()||theaterOptional.isEmpty()) {
            throw new NotFoundException("Theater or movie not found");
        }
        showing.setMovie(movieOptional.get());
        showing.setTheater(theaterOptional.get());
        showing.setStartTime(request.startTime());
        showing.setThreeDimensional(request.is3d());
        return showingRepository.save(showing);
    }

    public List<Showing> getShowings() {
        return showingRepository.findAllByStartTimeGreaterThanEqualOrderByStartTime(LocalDateTime.now());
    }

    public Showing getShowingById(Long id) {
        return showingRepository.findById(id).orElseThrow(() -> new NotFoundException("Showing not found with id: " + id));
    }

    public List<Showing> getShowingsForTheater(Long theaterId) {
        return showingRepository.findByTheater_IdOrderByStartTimeAsc(theaterId);
    }

    public List<Showing> getShowingsForMovie(Long movieId) {
        return showingRepository.findByMovie_Id(movieId);
    }

    public void deleteShowingById(Long id) {
        showingRepository.deleteById(id);
    }


}
