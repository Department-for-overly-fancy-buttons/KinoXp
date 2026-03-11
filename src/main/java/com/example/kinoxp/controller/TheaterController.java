package com.example.kinoxp.controller;

import com.example.kinoxp.dto.CreateTheaterRequest;
import com.example.kinoxp.model.Showing;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.service.ShowingService;
import com.example.kinoxp.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/theaters")
@RestController
public class TheaterController {


    private TheaterService theaterService;
    private ShowingService showingService;

    public TheaterController(ShowingService showingService, TheaterService theaterService) {
        this.showingService = showingService;
        this.theaterService = theaterService;
    }

    @GetMapping()
    public ResponseEntity<List<Theater>> getTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody CreateTheaterRequest theaterRequest) {
        return ResponseEntity.ok(theaterService.createTheater(theaterRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheaterById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/showings/{id}")
    public ResponseEntity<List<Showing>> getAllShowingsForTheater(@PathVariable Long id) {
        return ResponseEntity.ok(showingService.getShowingsForTheater(id));
    }

}
