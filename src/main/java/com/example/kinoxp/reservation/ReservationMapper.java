package com.example.kinoxp.reservation;

import com.example.kinoxp.showing.Showing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ReservationMapper {

    public static Reservation toReservation(CreateReservationRequest request, Showing showing){

        System.out.println("ticket: " + request.tickets() + ", ticket 1 row: " + request.tickets().getFirst().getSeatLabel());
        Reservation reservation = new Reservation(showing, request.email(), request.phoneNumber(), request.firstName(), request.lastName());
        List<Ticket> tickets = new ArrayList<>();

        for(Ticket ticket : request.tickets()) {
            ticket.setReservation(reservation);
            tickets.add(ticket);
        }
        reservation.setTickets(tickets);

        reservation.setTimeOfPurchase(LocalDateTime.now());
        return reservation;
    }

}
