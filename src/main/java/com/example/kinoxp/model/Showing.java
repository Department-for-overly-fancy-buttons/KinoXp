package com.example.kinoxp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showingId;
    //private Movie movie;
    private LocalDateTime startTime;
    private boolean isExtra;

    public Showing(int showingId, Movie movie, LocalDateTime startTime, boolean isExtra) {
        this.showingId = showingId;
        //this.movie = movie;
        this.startTime = startTime;
        this.isExtra = isExtra;
    }
    public Showing() {}

    public long getShowingId() {
        return showingId;
    }

    public void setShowingId(long showingId) {
        this.showingId = showingId;
    }

//    public Movie getMovies() {
//        return movie;
//    }
//
//    public void setMovies(Movie movie) {
//        this.movie = movie;
//    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }
}
