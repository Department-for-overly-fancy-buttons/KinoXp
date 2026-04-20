package com.example.kinoxp.reservation;

import com.example.kinoxp.theater.Theater;
import com.example.kinoxp.theater.TheaterService;
import com.example.kinoxp.ticket.Ticket;
import com.example.kinoxp.ticket.TicketServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/api/reservations")
class ReservationController {

    private final ReservationService reservationService;
    private final TheaterService theaterService;
    private final TicketServiceInterface ticketService;


    ReservationController(ReservationService reservationService, TheaterService theaterService, TicketServiceInterface ticketService){
        this.reservationService = reservationService;
        this.theaterService = theaterService;
        this.ticketService = ticketService;

    }

    @GetMapping()
    ResponseEntity<List<Reservation>> getReservations() {

        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/tickets/{id}")
    ResponseEntity<List<Ticket>> getTicketsForReservation(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getAllTicketsForReservation(reservationService.getReservationById(id)));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    //@Transactional
    ResponseEntity<Reservation> addReservation(@RequestBody CreateReservationRequest reservationRequest) {

        Reservation reservation1 = reservationService.createReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation1);
    }

    @GetMapping("/theaters/{theaterId}")
    ResponseEntity<Theater> getTheater(@PathVariable Long theaterId) {
        Theater theater = theaterService.getTheaterById(theaterId);
        return ResponseEntity.ok(theater);
    }

//    @GetMapping("/showings/{showingId}/reserved-seats")
//    ResponseEntity<List<Reservation>> getReservedSeats(@PathVariable Long showingId) {
//        List<Reservation> reservedSeats = reservationService.getAllTicketsForShowing(showingId);
//        return ResponseEntity.ok(reservedSeats);
//    }


}
