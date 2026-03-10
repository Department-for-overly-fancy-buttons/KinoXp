package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity

public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String theaterName;
    private String location;
    private int numberOfRows;
    private int seatsPerRow;

    public Theater( String theaterName, int numberOfRows, int seatsPerRow, String location) {
        this.theaterName = theaterName;
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Theater() {}

    public long getId() {
        return id;
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
