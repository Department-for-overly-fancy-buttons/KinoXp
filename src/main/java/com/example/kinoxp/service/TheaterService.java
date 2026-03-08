package com.example.kinoxp.service;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater){
        return theaterRepository.save(theater);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id).orElseThrow(() -> new NotFoundException("Theater not found with id: " + id));
    }

    public void deleteTheaterById(Long id) {
        theaterRepository.deleteById(id);
    }

    public Theater updateTheater(Long id, Theater theater) {
        Theater newTheater = getTheaterById(id);
        newTheater.setNumberOfRows(theater.getNumberOfRows());
        newTheater.setSeatsPerRow(theater.getSeatsPerRow());
        newTheater.setTheaterName(theater.getTheaterName());
        return theaterRepository.save(newTheater);
    }

}
