package com.example.kinoxp.model;

public class Movies {
    private String titel;
    private String description;
    private int movieId;
    private boolean ageLimit;
    private double duration;

    public Movies(String titel, String description, int movieId, boolean ageLimit, double duration) {
        this.titel = titel;
        this.description = description;
        this.movieId = movieId;
        this.ageLimit = ageLimit;
        this.duration = duration;
    }
    public Movies() {}

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(boolean ageLimit) {
        this.ageLimit = ageLimit;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
