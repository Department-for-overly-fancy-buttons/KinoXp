package com.example.kinoxp.service;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Showing;
import com.example.kinoxp.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {

    private final ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public Showing createShowing(Showing showing) {
        return showingRepository.save(showing);
    }

    public List<Showing> getShowings() {
        return showingRepository.findAll();
    }

    public Showing getShowingById(Long id) {
        return showingRepository.findById(id).orElseThrow(() -> new NotFoundException("Showing not found with id: " + id));
    }

    public List<Showing> getShowingsForTheater(Long theaterId) {
        return showingRepository.findByTheater_IdOrderByStartTimeDesc(theaterId);
    }

    public List<Showing> getShowingsForMovie(Long movieId){
        return showingRepository.findByMovie_Id(movieId);
    }

    public void deleteShowingById(Long id){
        showingRepository.deleteById(id);
    }


}
