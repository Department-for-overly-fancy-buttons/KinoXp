package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titel;
    @Column(length = 5000)
    private String description;
    private int pgRating;
    private double duration;
    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private List<Category> categories = new ArrayList<>();


    public Movie(String titel, String description, int pgRating, double duration, List<Category> categories) {
        this.titel = titel;
        this.description = description;
        this.pgRating = pgRating;
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

    public int getPgRating() {
        return pgRating;
    }

    public void setPgRating(int pgRating) {
        this.pgRating = pgRating;
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

    //Methods below may be Changed
    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
    }

}
