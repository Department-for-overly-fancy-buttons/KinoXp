package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Reservation reservation;
    //private Showing showing;
    private LocalDateTime Time;
    private int rowNumber;
    private int seatNumber;
    private double price;

    public Ticket() {}

    public Ticket(Showing showing, LocalDateTime time,
                  int rowNumber, int seatNumber, double price) {
        this.id = id;
        //this.showing = showing;
        Time = time;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.price = price;
    }


    public String getSeatLabel() {
        return "Row " + rowNumber + ", Seat " + seatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(int ticketId) {
        this.id = id;
    }

//    public Showing getShowing() {
//        return showing;
//    }
//
//    public void setShowing(Showing showing) {
//        this.showing = showing;
//    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
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
