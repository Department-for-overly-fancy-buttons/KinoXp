package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonBackReference
    private Reservation reservation;
    private int rowNumberssss;
    private int seatNumber;
    private double price;

    public Ticket() {
    }

    public Ticket(Reservation reservation,
                  int rowNumberssss, int seatNumber, double price) {
        this.reservation = reservation;
        this.rowNumberssss = rowNumberssss;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getSeatLabel() {
        return "Row " + rowNumberssss + ", Seat " + seatNumber;
    }

    public long getId() {
        return id;
    }



    public int getRowNumberssss() {
        return rowNumberssss;
    }

    public void setRowNumberssss(int rowNumberssss) {
        this.rowNumberssss = rowNumberssss;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
