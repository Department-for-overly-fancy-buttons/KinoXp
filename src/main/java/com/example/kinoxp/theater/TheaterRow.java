package com.example.kinoxp.theater;

import com.example.kinoxp.reservation.TicketType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class TheaterRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"row_number\"")
    private int rowNumber;

    @ManyToOne
    @JsonBackReference
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "ticket_type")
    private TicketType ticketType;

    public TheaterRow() {
    }

    public TheaterRow(int rowNumber, Theater theater, TicketType ticketType) {
        this.rowNumber = rowNumber;
        this.theater = theater;
        this.ticketType = ticketType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }


}
