package com.example.kinoxp.model;

import java.time.LocalDateTime;

public class Showing {
    private int showingId;
    private Movies movies;
    private LocalDateTime startTime;
    private boolean isExtra;

    public Showing(int showingId, Movies movies, LocalDateTime startTime, boolean isExtra) {
        this.showingId = showingId;
        this.movies = movies;
        this.startTime = startTime;
        this.isExtra = isExtra;
    }
    public Showing() {}

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

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
