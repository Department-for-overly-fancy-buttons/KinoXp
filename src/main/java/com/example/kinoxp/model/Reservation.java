package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;
    private LocalDateTime timeOfPurchase;
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "reservation")
    @JsonManagedReference
    private List<Ticket> tickets;

    public Reservation() {
    }

    public Reservation(Showing showing, String email, String phoneNumber, String firstName, String lastName) {
        this.showing = showing;
        this.tickets = new ArrayList<>();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    public Showing getShowing() {
        return showing;
    }

    public void setTheaterId(Showing showing) {
        this.showing = showing;
    }

    public long getReservationId() {
        return id;
    }

    public void setReservationId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        if(this.timeOfPurchase == null) {
            this.timeOfPurchase = timeOfPurchase;
        }
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

    //    method to add ticket
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}