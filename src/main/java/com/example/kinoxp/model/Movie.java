package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titel;
    @Column(length = 5000)
    private String description;
    private boolean isAgeRestricted;
    private double duration;
    private Category category;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="movie_categories")
    @Column(name="Category")
    private List<Category> categories;

    public enum Category{Horror, Scifi, Comedy, Action, Romance}


    public Movie(String titel, String description, boolean isAgeRestricted, double duration, List<Category> categories) {
        this.titel = titel;
        this.description = description;
        this.isAgeRestricted = isAgeRestricted;
        this.duration = duration;
        this.categories = categories;

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

    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }

    public void setAgeRestricted(boolean ageRestricted) {
        this.isAgeRestricted = ageRestricted;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
