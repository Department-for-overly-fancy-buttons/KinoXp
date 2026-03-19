package com.example.kinoxp.reservation;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.showing.Showing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public TicketService(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
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

    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    public TicketType createTicketType(TicketType ticketType) {
        return ticketTypeRepository.save(ticketType);
    }
}
