package com.example.kinoxp.reservation;

import jakarta.persistence.*;

@Entity
public class TicketType {
    @Id
    private String ticketType;
    private double price;

    public TicketType(String ticketType, double price) { this.ticketType=ticketType; this.price = price; }

    public TicketType() {}

    public String getTicketType() {
        return  ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType=ticketType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

