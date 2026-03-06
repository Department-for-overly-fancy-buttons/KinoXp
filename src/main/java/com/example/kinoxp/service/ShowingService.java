package com.example.kinoxp.service;

import com.example.kinoxp.model.Showing;
import com.example.kinoxp.repository.ShowingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {

    private final ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository){
        this.showingRepository = showingRepository;
    }

    public List<Showing> getShowings() {

        return showingRepository.findAll();

    }
}
