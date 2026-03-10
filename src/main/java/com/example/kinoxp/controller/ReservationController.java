package com.example.kinoxp.controller;

import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.service.MovieService;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.service.ReservationService;
import com.example.kinoxp.service.TheaterService;
import org.springframework.http.HttpStatus;
import com.example.kinoxp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController implements ReservationControllerInterface {

    private final ReservationService reservationService;
    private final TheaterService theaterService;
    private final TicketService ticketService;


    public ReservationController(ReservationService reservationService, TheaterService theaterService, TicketService ticketService){
        this.reservationService = reservationService;
        this.theaterService = theaterService;
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

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation reservation1 = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation1);
    }

    @GetMapping("/theaters/{theaterId}")
    public ResponseEntity<Theater> getTheater(@PathVariable Long theaterId) {
        Theater theater = theaterService.getTheaterById(theaterId);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/showings/{showingId}/reserved-seats")
    public ResponseEntity<List<Reservation>> getReservedSeats(@PathVariable Long showingId) {
        List<Reservation> reservedSeats = reservationService.getAllReservationsForShowing(showingId);
        return ResponseEntity.ok(reservedSeats);
    }


}
