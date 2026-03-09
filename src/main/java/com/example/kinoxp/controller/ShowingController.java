package com.example.kinoxp.controller;

import com.example.kinoxp.model.Showing;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.service.ShowingService;
import com.example.kinoxp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/api/showing")
@RequestMapping("/api/showings")
@RestController
public class ShowingController {

    private ShowingService showingService;
    private TicketService ticketService;

    public ShowingController(ShowingService showingService, TicketService ticketService) {
        this.showingService = showingService;
        this.ticketService = ticketService;
    }

    @GetMapping()
    public ResponseEntity<List<Showing>> getShowings() {
        return ResponseEntity.ok(showingService.getShowings());
    }

    @PostMapping
    public ResponseEntity<Showing> createShowing(@RequestBody Showing showing) {
        return ResponseEntity.ok(showingService.createShowing(showing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowing(@PathVariable Long id) {
        showingService.deleteShowingById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<Ticket>> getAllTicketsForShowing(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getAllTicketsForShowing(showingService.getShowingById(id)));
    }

}
