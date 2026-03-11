package com.example.kinoxp.controller;

import com.example.kinoxp.mapper.ReservationMapper;
import com.example.kinoxp.model.*;
import com.example.kinoxp.service.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController implements ReservationControllerInterface {

    private final ReservationService reservationService;
    private final TheaterService theaterService;
    private final TicketService ticketService;
    private final ShowingService showingService;


    public ReservationController(ReservationService reservationService, TheaterService theaterService, TicketService ticketService, ShowingService showingService){
        this.reservationService = reservationService;
        this.theaterService = theaterService;
        this.ticketService = ticketService;
        this.showingService = showingService;

    }

    @GetMapping()
    public ResponseEntity<List<Reservation>> getReservations() {

        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
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

    @PostMapping
    //@Transactional
    public ResponseEntity<Reservation> addReservation(@RequestBody CreateReservationRequest reservationRequest) {
        Showing foundShowing = showingService.getShowingById(reservationRequest.showingId());
        Reservation reservation = ReservationMapper.toReservation(reservationRequest, foundShowing);
        Reservation reservation1 = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation1);
    }

    @GetMapping("/theaters/{theaterId}")
    public ResponseEntity<Theater> getTheater(@PathVariable Long theaterId) {
        Theater theater = theaterService.getTheaterById(theaterId);
        return ResponseEntity.ok(theater);
    }

//    @GetMapping("/showings/{showingId}/reserved-seats")
//    public ResponseEntity<List<Reservation>> getReservedSeats(@PathVariable Long showingId) {
//        List<Reservation> reservedSeats = reservationService.getAllTicketsForShowing(showingId);
//        return ResponseEntity.ok(reservedSeats);
//    }


}
