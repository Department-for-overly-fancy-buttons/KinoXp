package com.example.kinoxp.controller;

import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.service.ReservationService;
import com.example.kinoxp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/api/showing")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController implements ReservationControllerInterface {

    private final ReservationService reservationService;
    private final TicketService ticketService;


    public ReservationController(ReservationService reservationService, TicketService ticketService) {

        this.reservationService = reservationService;
        this.ticketService = ticketService;

    }

    @GetMapping()
    public ResponseEntity<List<Reservation>> getReservations() {

        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<Ticket>> getTicketsForReservation(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getAllTicketsForReservation(reservationService.getReservationById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

}
