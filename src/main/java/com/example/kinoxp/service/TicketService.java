package com.example.kinoxp.service;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Showing;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllTicketsForShowing(Showing showing) {
        return null;
    }

    public List<Ticket> getAllTicketsForReservation(Reservation reservation) {
        return ticketRepository.findByReservationId(reservation.getReservationId());
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found with id: " + id));
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }

}
