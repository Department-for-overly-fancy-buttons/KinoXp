package com.example.kinoxp.showing;

import com.example.kinoxp.reservation.Ticket;
import com.example.kinoxp.reservation.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/showings")
@RestController
class ShowingController {

    private ShowingService showingService;
    private ReservationService reservationService;

    public ShowingController(ShowingService showingService, ReservationService reservationService) {
        this.showingService = showingService;
        this.reservationService = reservationService;
    }

    @GetMapping()
    public ResponseEntity<List<Showing>> getShowings() {
        return ResponseEntity.ok(showingService.getShowings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showing> getShowingById(@PathVariable Long id) {
        return ResponseEntity.ok(showingService.getShowingById(id));
    }

    @PostMapping
    public ResponseEntity<Showing> createShowing(@RequestBody CreateShowingRequest request) {
        return ResponseEntity.ok(showingService.createShowing(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable Long id) {
        showingService.deleteShowingById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<Ticket>> getAllTicketsForShowing(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getAllTicketsForShowing(id));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<List<Showing>> getAllShowingsForMovie(@PathVariable Long id) {
        return ResponseEntity.ok(showingService.getShowingsForMovie(id));
    }
}
