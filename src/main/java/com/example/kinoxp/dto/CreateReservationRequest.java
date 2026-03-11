package com.example.kinoxp.dto;

import com.example.kinoxp.model.Ticket;

import java.util.List;

public record CreateReservationRequest(String firstName,
                                       String lastName,
                                       String email,
                                       String phoneNumber,
                                       List<Ticket> tickets,
                                       long showingId) {
}
