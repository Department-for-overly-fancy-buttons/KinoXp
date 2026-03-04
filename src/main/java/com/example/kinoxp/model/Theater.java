package com.example.kinoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theaterId;
    private String theaterName;
    private int numberOfRows;
    private int seatsPerRow;

    public Theater(int theaterId, String theaterName, int numberOfRows, int seatsPerRow) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
    }
    public Theater() {}

    public long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(long theaterId) {
        this.theaterId = theaterId;
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
