package com.example.kinoxp.theater;

import com.example.kinoxp.showing.Showing;
import com.example.kinoxp.showing.ShowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RequestMapping("/api/theaters")
@RestController
class TheaterController {


    private TheaterService theaterService;
    private ShowingService showingService;

    TheaterController(ShowingService showingService, TheaterService theaterService) {
        this.showingService = showingService;
        this.theaterService = theaterService;
    }

    @GetMapping()
    ResponseEntity<List<Theater>> getTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @PostMapping
    ResponseEntity<Theater> createTheater(@RequestBody CreateTheaterRequest theaterRequest) {
        return ResponseEntity.ok(theaterService.createTheater(theaterRequest));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheaterById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/showings/{id}")
    ResponseEntity<List<Showing>> getAllShowingsForTheater(@PathVariable Long id) {
        return ResponseEntity.ok(showingService.getShowingsForTheater(id));
    }

}
