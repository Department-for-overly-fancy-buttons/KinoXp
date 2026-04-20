package com.example.kinoxp.ticket;

import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.reservation.Reservation;
import com.example.kinoxp.showing.Showing;

import java.util.List;

public interface TicketServiceInterface {

    public Ticket createTicket(Ticket ticket);

    public List<Ticket> getAllTickets();

    public List<Ticket> getAllTicketsForShowing(Showing showing);


    public List<Ticket> getAllTicketsForReservation(Reservation reservation);

    public Ticket getTicketById(Long id);

    public void deleteTicketById(Long id);

    public List<TicketType> getAllTicketTypes();

    public TicketType createTicketType(TicketType ticketType);
}
