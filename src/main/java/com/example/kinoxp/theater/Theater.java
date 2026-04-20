package com.example.kinoxp.theater;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "theater", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<TheaterRow> theaterRows;

    public Theater(String theaterName, int numberOfRows, int seatsPerRow, String location) {
        this.theaterName = theaterName;
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
        this.location = location;
        this.theaterRows = new ArrayList<>();
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

    public Theater() {
    }

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

    public List<TheaterRow> getTheaterRows() {
        return theaterRows;
    }

    public void setTheaterRows(List<TheaterRow> theaterRows) {
        this.theaterRows = theaterRows;
    }

    public void addTheaterRow(TheaterRow theaterRow) {
        theaterRows.add(theaterRow);
    }

    public void removeTheaterRow(TheaterRow theaterRow) {
        theaterRows.remove(theaterRow);
    }

}
