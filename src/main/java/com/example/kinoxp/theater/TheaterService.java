package com.example.kinoxp.theater;

import com.example.kinoxp.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(CreateTheaterRequest theaterRequest) {
        return theaterRepository.save(TheaterMapper.toTheater(theaterRequest));
    }

    List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id).orElseThrow(() -> new NotFoundException("Theater not found with id: " + id));
    }

    void deleteTheaterById(Long id) {
        theaterRepository.deleteById(id);
    }

    Theater updateTheater(Long id, Theater theater) {
        Theater newTheater = getTheaterById(id);
        newTheater.setNumberOfRows(theater.getNumberOfRows());
        newTheater.setSeatsPerRow(theater.getSeatsPerRow());
        newTheater.setTheaterName(theater.getTheaterName());
        return theaterRepository.save(newTheater);
    }


}
