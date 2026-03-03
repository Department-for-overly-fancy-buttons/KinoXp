package com.example.kinoxp.model;

public class Theater {
    private int theaterId;
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

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
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
