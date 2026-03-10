package com.example.kinoxp.model;

import java.util.List;

public record CreateReservationRequest(String firstName,
                                       String lastName,
                                       String email,
                                       String phoneNumber,
                                       List<Ticket> tickets,
                                       long showingId) {
}
