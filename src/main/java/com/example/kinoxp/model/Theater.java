package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity

public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String theaterName;
    private int numberOfRows;
    private int seatsPerRow;

    public Theater( String theaterName, int numberOfRows, int seatsPerRow) {
        this.theaterName = theaterName;
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
    }

    public Theater() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
}
