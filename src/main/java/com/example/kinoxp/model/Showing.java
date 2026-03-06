package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    private LocalDateTime startTime;
    private boolean is3d;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Showing(Movie movie, Theater theater, LocalDateTime startTime, boolean is3d) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.is3d = is3d;
    }
    public Showing() {}

    public long getId() {
        return id;
    }

    public void setId(long showingId) {
        this.id = showingId;
    }

    public Movie getMovies() {
        return movie;
    }

    public void setMovies(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean is3d() {
        return is3d;
    }

    public void set3d(boolean is3d) {
        this.is3d = is3d;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
