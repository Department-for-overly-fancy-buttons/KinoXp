package com.example.kinoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TicketId;
    //private Showing showing;
    //private Movie movie;
    private LocalDateTime Time;
    private int rowNumber;
    private int seatNumber;
    private double price;

    public Ticket() {}

    public Ticket(int ticketId, Showing showing, Movie movie, LocalDateTime time,
                  int rowNumber, int seatNumber, double price) {
        TicketId = ticketId;
        //this.showing = showing;
        //this.movie = movie;
        Time = time;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.price = price;
    }


    public String getSeatLabel() {
        return "Row " + rowNumber + ", Seat " + seatNumber;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

//    public Showing getShowing() {
//        return showing;
//    }
//
//    public void setShowing(Showing showing) {
//        this.showing = showing;
//    }
//
//    public Movie getMovies() {
//        return movie;
//    }
//
//    public void setMovies(Movie movie) {
//        this.movie = movie;
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
