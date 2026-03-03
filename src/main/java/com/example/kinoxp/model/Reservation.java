package com.example.kinoxp.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int theaterId;
    private int reservationId;
    private LocalDateTime reservationDate;
    private List<Ticket> tickets;
    public String email;
    public String phoneNumber;

    public Reservation() {}

    public Reservation(int theaterId, int reservationId, LocalDateTime reservationDate, List<Ticket> tickets,
             String email, String phoneNumber) {
        this.theaterId = theaterId;
        this.reservationId = reservationId;
        this.reservationDate = LocalDateTime.now();
        this.tickets = new ArrayList<>();;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //method to add ticket
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

}