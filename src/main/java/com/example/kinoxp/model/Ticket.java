package com.example.kinoxp.model;

import java.time.LocalDateTime;

public class Ticket {
    private int TicketId;
    private Showing showing;
    private Movies movies;
    private LocalDateTime Time;
    private int rowNumber;
    private int seatNumber;
    private double price;

    public Ticket() {}

    public Ticket(int ticketId, Showing showing, Movies movies, LocalDateTime time,
                  int rowNumber, int seatNumber, double price) {
        TicketId = ticketId;
        this.showing = showing;
        this.movies = movies;
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

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

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
