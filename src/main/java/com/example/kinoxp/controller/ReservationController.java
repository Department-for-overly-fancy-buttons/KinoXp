package com.example.kinoxp.controller;

import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.service.ReservationService;
import com.example.kinoxp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class ReservationController implements ReservationControllerInterface {

    private final ReservationService reservationService;
    private final TicketService ticketService;


    public ReservationController(ReservationService reservationService, TicketService ticketService) {

        this.reservationService = reservationService;
        this.ticketService = ticketService;

    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {

        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<List<Ticket>> getTicketsForReservation(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getAllTicketsForReservation(reservationService.getReservationById(id)));
    }

}
