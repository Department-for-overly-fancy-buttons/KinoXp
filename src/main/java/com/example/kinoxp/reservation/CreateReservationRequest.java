package com.example.kinoxp.reservation;

import com.example.kinoxp.ticket.Ticket;

import java.util.List;

public record CreateReservationRequest(String firstName,
                                       String lastName,
                                       String email,
                                       String phoneNumber,
                                       List<Ticket> tickets,
                                       long showingId) {
}
