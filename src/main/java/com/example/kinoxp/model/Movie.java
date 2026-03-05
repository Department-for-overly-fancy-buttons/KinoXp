package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titel;
    @Column(length = 5000)
    private String description;
    private boolean ageLimit;
    private double duration;

    public Movie(String titel, String description, boolean ageLimit, double duration) {
        this.titel = titel;
        this.description = description;
        this.ageLimit = ageLimit;
        this.duration = duration;
    }
    public Movie() {}

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
