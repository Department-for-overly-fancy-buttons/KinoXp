package com.example.kinoxp.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "movie", "theater", "startTime", "isThreeDimensional"})
@Entity

public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    private LocalDateTime startTime;
    private boolean isThreeDimensional;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Showing(Movie movie, Theater theater, LocalDateTime startTime, boolean isThreeDimensional) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.isThreeDimensional = isThreeDimensional;
    }
    public Showing() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isThreeDimensional() {
        return isThreeDimensional;
    }

    public void setThreeDimensional(boolean threeDimensional) {
        isThreeDimensional = threeDimensional;
    }
}
