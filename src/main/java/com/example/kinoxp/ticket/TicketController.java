package com.example.kinoxp.ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/api/tickets")
@RestController
class TicketController {

    private TicketService ticketService;

    TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping()
    ResponseEntity<List<Ticket>> getTicket() {

        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/types")
    ResponseEntity<List<TicketType>> getTicketTypes() {
        return ResponseEntity.ok(ticketService.getAllTicketTypes());
    }

    @GetMapping("/{id}")
    ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping("/new/type")
    ResponseEntity<TicketType> addTicketType(@RequestBody TicketType ticketType) {
        return ResponseEntity.ok(ticketService.createTicketType(ticketType));
    }

}
